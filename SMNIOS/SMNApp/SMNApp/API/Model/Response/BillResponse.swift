//
//  BillResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/6/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class BillResponse: BaseResponse {

    public var billId: Int?
    public var billCode: String = ""
    public var customerId: Int?
    public var customerName: String = ""
    public var customerPhone: String = ""
    public var address: String = ""
    public var province: ResourceModel?
    public var district: ResourceModel?
    public var ward: ResourceModel?
    public var agency: ResourceModel?
    public var description: String = ""
    public var billStepId: Int?
    public var billPrice: Int64 = 0
    public var billRefundCost: Int64 = 0
    public var billDebt: Int64 = 0
    public var billDetails: [BillDetailResponse] = [BillDetailResponse]()
    public var isActive: Bool = true


    public required init?(map: Map) {
        super.init(map: map)
    }
    
    public convenience init?(){
        self.init(map: Map(mappingType: .fromJSON, JSON: [String:Any]()))
    }

    override public func mapping(map: Map) {
        super.mapping(map: map)

        billId <- map["bill_id"]
        billCode <- map["bill_code"]
        customerId <- map["customer_id"]
        customerName <- map["customer_name"]
        customerPhone <- map["customer_phone"]
        address <- map["address"]
        province <- map["province"]
        district <- map["district"]
        ward <- map["ward"]
        agency <- map["agency"]
        description <- map["description"]
        billStepId <- map["bill_step_id"]
        billPrice <- map["bill_price"]
        billRefundCost <- map["bill_refund_cost"]
        billDebt <- map["bill_debt"]
        billDetails <- map["bill_details"]
        isActive <- map["is_active"]
    }
}
