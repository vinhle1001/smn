//
//  WardResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class WardResponse : BaseResponse{
    public var data = [WardItem]()
    
    public required init?(map: Map) {
        super.init(map: map)
    }
    
    override public func mapping(map: Map) {
        super.mapping(map: map)
        
        data <- map["wards"]
    }
}

public class WardItem:Mappable, Equatable {
    public var id:Int!
    public var name:String!
    public var type:String?
    public var districtId:Int!
    
    public required init?(map: Map) {
    }
    
    public func mapping(map: Map) {
        
        id <- map["id"]
        name <- map["name"]
        type <- map["type"]
        districtId <- map["district_id"]
    }
    
    public static func == (lhs: WardItem, rhs: WardItem) -> Bool {
        return lhs.id == rhs.id
    }
}
