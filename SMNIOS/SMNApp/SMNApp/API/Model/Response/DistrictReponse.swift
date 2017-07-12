//
//  DistrictReponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class DistrictResponse : BaseResponse{
    public var data = [DistrictItem]()
    
    public required init?(map: Map) {
        super.init(map: map)
    }
    
    override public func mapping(map: Map) {
        super.mapping(map: map)
        
        data <- map["districts"]
    }
}

public class DistrictItem:Mappable, Equatable {
    public var id:Int!
    public var name:String!
    public var type:String?
    public var provinceId:Int!
    
    public required init?(map: Map) {
    }
    
    public func mapping(map: Map) {
        
        id <- map["id"]
        name <- map["name"]
        type <- map["type"]
        provinceId <- map["province_id"]
    }
    
    public static func == (lhs: DistrictItem, rhs: DistrictItem) -> Bool {
        return lhs.id == rhs.id
    }
}
