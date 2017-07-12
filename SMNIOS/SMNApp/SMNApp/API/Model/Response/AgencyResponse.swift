//
//  AgencyResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class AgencyResponse : BaseResponse{
    
    public var data = [AgencyItem]()
    
    public required init?(map: Map) {
        super.init(map: map)
    }
    
    override public func mapping(map: Map) {
        super.mapping(map: map)
        
        data <- map["agencies"]
    }
}

public class AgencyItem:Mappable, Equatable {
    
    public var agencyId:Int!
    public var agencyCode:String!
    public var agencyName:String!
    
    public required init?(map: Map) {
    }
    
    public func mapping(map: Map) {
        
        agencyId <- map["agency_id"]
        agencyCode <- map["agency_code"]
        agencyName <- map["agency_name"]
    }
    
    public static func == (lhs: AgencyItem, rhs: AgencyItem) -> Bool {
        return lhs.agencyId == rhs.agencyId
    }
}
