//
//  ResourceModel.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/6/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class ResourceModel: Mappable, Equatable {

    public var id: Int?
    public var name: String?
    public var type: String?
    public var sign: String?
    public var icon: String?

    public required init?(map: Map) {
    }

    public func mapping(map: Map) {

        id <- map["id"]
        name <- map["name"]
        type <- map["type"]
        sign <- map["sign"]
        icon <- map["icon"]
    }
    
    public static func ==(lhs: ResourceModel, rhs: ResourceModel) -> Bool{
        return lhs.id == rhs.id
            && lhs.name == rhs.name
            && lhs.type == rhs.type
            && lhs.sign == rhs.sign
            && lhs.icon == rhs.icon
    }
}
