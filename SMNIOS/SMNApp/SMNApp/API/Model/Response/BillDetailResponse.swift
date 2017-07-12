//
//  BillDetailResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/6/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class BillDetailResponse: Mappable {

    public var billDetailId: Int?;
    public var product: ResourceModel?;
    public var productPrice: Int64 = 0;
    public var productQuantity: Int = 0;
    public var productReturnedQuantity: Int = 0;
    public var refundCost: Int64 = 0;
    public var description: String = "";
    public var isActive: Bool = true;


    public required init?(map: Map) {
    }

    public func mapping(map: Map) {
        billDetailId <- map["bill_detail_id"]
        product <- map["product"]
        productPrice <- map["product_price"]
        productQuantity <- map["product_quantity"]
        productReturnedQuantity <- map["product_returned_quantity"]
        refundCost <- map["refund_cost"]
        description <- map["description"]
        isActive <- map["is_active"]
    }
}
