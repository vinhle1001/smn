//
//  BillSearchResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/6/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class BillSearchResponse: BaseResponse {

    public var data: [BillSearchItem]!

    public required init?(map: Map) {
        super.init(map: map)
    }

    override public func mapping(map: Map) {
        super.mapping(map: map)

        data <- map["data"]
    }
}

public class BillSearchItem: Mappable {
    public var customerId: Int!
    public var customerName: String!
    public var billId: Int!
    public var billPrice: Int64!
    public var createDate: Int64!
    public var billStepId: Int!

    public required init?(map: Map) {
    }

    public func mapping(map: Map) {

        customerId <- map["bill_id"]
        customerName <- map["bill_code"]
        billId <- map["customer_id"]
        billPrice <- map["customer_name"]
        createDate <- map["customer_phone"]
        billStepId <- map["address"]
    }
}
