//
//  CustomerSearchResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/1/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class CustomerSearchResponse : BaseResponse{
    public var data = [CustomerSearchItem]()
    
    public required init?(map: Map) {
        super.init(map: map)
    }
    
    override public func mapping(map: Map) {
        super.mapping(map: map)
        
        data <- map["data"]
    }
}

public class CustomerSearchItem: Mappable{
    public var customerId: Int!
    public var fullName:String!
    public var phoneNumber:String!
    
    public required init?(map: Map) {
        
    }
    
    public func mapping(map: Map) {
        
        customerId <- map["customer_id"]
        fullName <- map["customer_full_name"]
        phoneNumber <- map["customer_phone_number"]
    }
}
