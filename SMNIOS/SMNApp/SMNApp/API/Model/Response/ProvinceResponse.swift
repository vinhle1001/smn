//
//  ProvinceResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class ProvinceResponse: BaseResponse {
    public var data = [ProvinceItem]()

    public required init?(map: Map) {
        super.init(map: map)
    }

    override public func mapping(map: Map) {
        super.mapping(map: map)

        data <- map["provinces"]
    }
}

public class ProvinceItem: Mappable, Equatable {
    public var id: Int!
    public var name: String!
    public var type: String?

    public required init?(map: Map) {
    }

    public func mapping(map: Map) {

        id <- map["id"]
        name <- map["name"]
        type <- map["type"]
    }

    public static func == (lhs: ProvinceItem, rhs: ProvinceItem) -> Bool {
        return lhs.id == rhs.id
    }

}
