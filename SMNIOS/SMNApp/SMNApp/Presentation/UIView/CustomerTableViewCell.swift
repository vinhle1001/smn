//
//  CustomerTableViewCell.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/1/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit

class CustomerTableViewCell: UITableViewCell {

    @IBOutlet weak var imageViewCustomer: UIImageView!
    @IBOutlet weak var labelCustomerName: UILabel!
    @IBOutlet weak var labelCustomerPhone: UILabel!
    @IBOutlet weak var btnNewBill: UIButton!
    @IBOutlet weak var btnBillHistory: UIButton!
    
    private var _item:CustomerSearchItem?
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    public func setItem(item:CustomerSearchItem){
        self._item = item
        
        //Display
        labelCustomerName.text = item.fullName
        labelCustomerPhone.text = item.phoneNumber
    }
    
    public func setRow(index: Int){
        btnNewBill.tag = index
        btnBillHistory.tag = index
    }
}
