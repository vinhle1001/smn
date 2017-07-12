//
//  BillModifyViewController.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/6/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit
import Eureka

public enum EditMode {
    case Create
    case Edit
}

class BillModifyViewController: FormViewController {

    private enum Row: String {
        case Provice = "proivce"
        case District = "district"
        case Ward = "ward"
        case store = "store"
    }
    private var client: ResourceClient = ResourceClient()

    private var lastProvince: ProvinceItem?
    private var lastDistrict: DistrictItem?

    @IBOutlet weak var lbTitle: UINavigationItem!

    public var customerId: Int!
    public var mode: EditMode!
    public var bill: BillResponse?

    override func viewDidLoad() {
        super.viewDidLoad()

        //Check input
        switch (mode!) {
        case .Create:
            bill = BillResponse()
        case .Edit:
            if (bill == nil) {
                AlertEx.ShowAlert(controller: self, title: "Lỗi", message: "Chưa chỉ định hóa đơn")
            }
        }

        //Create form
        form +++ Section("Thông tin hóa đơn")
        <<< ReadOnlyTextRow() { row in
            row.title = "Mã HĐ"
            row.placeholder = ""
        }
        <<< TextRow() { row in
            row.title = "Khách hàng"
            row.placeholder = "Bắt buộc"
            row.add(rule: RuleRequired(msg: "Hãy điền tên khách hàng"))
        }
        <<< PhoneRow() { row in
            row.title = "SĐT"
            row.placeholder = "Bắt buộc"
            row.add(rule: RuleRequired(msg: "Hãy điền số diện thoại"))
        }
        <<< TextRow() { row in
            row.title = "Địa chỉ"
            row.placeholder = "Bắt buộc"
            row.add(rule: RuleRequired(msg: "Hãy điền địa chỉ"))
        }
        <<< SearchablePickerInlineRow<ProvinceItem>() { row in
            row.title = "Tỉnh/TP"
            row.options = StaticVariables.provices
            row.tag = Row.Provice.rawValue
            row.add(rule: RuleRequired(msg: "Hãy chọn tỉnh"))
            row.displayValueFor = { item in "\(item?.type ?? "") \(item?.name ?? "")" }
            row.onCollapseInlineRowEx { cell, row, pickerRow in

                if let province = row.value, let next = self.form.rowBy(tag: Row.District.rawValue) as? SearchablePickerInlineRow<DistrictItem> {

                    //Remove value
                    if (province != self.lastProvince) {

                        self.lastProvince = province
                        next.value = nil

                        if let next2 = self.form.rowBy(tag: Row.Ward.rawValue) as? SearchablePickerInlineRow<WardItem> {
                            next2.value = nil
                            next2.reload()
                        }
                    }

                    //Load new value for next
                    self.client.districts(provinceId: province.id, { response in

                        if ResponseHelper.CheckError(controller: self, response), let data = response.data {

                            next.options = data.data.sorted(by: { (x, y) -> Bool in return x.name < y.name })
                            next.reload()
                        }
                    })
                }
            }
        }
        <<< SearchablePickerInlineRow<DistrictItem>() { row in
            row.title = "Quận/Huyện"
            row.tag = Row.District.rawValue
            row.add(rule: RuleRequired(msg: "Hãy chọn quận/huyện"))
            row.displayValueFor = { item in "\(item?.type ?? "") \(item?.name ?? "")" }
            row.onCollapseInlineRowEx { cell, row, pickerRow in

                if let district = row.value, let next = self.form.rowBy(tag: Row.Ward.rawValue) as? SearchablePickerInlineRow<WardItem> {

                    //Remove value
                    if (district != self.lastDistrict) {

                        self.lastDistrict = district
                        next.value = nil
                    }


                    self.client.wards(districtId: district.id, { response in

                        if ResponseHelper.CheckError(controller: self, response), let data = response.data {

                            next.options = data.data.sorted(by: { (x, y) -> Bool in return x.name < y.name })
                            next.reload()
                        }
                    })
                }
            }
        }
        <<< SearchablePickerInlineRow<WardItem>() { row in
            row.title = "Phường/Xã"
            row.tag = Row.Ward.rawValue
            row.add(rule: RuleRequired(msg: "Hãy chọn phường xã"))
            row.displayValueFor = { item in "\(item?.type ?? "") \(item?.name ?? "")" }
        }
        <<< SearchablePickerInlineRow<AgencyItem>() { row in
            row.title = "Cửa hàng"
            row.options = StaticVariables.agencies
            row.tag = Row.store.rawValue
            row.add(rule: RuleRequired(msg: "Hãy chọn cửa hàng"))
            row.displayValueFor = { item in item?.agencyName }
        }
    }
}
