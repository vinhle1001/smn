//
//  ProductTypeResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class ProductTypeResponse: BaseResponse {
    public var data = [ProductTypeItem]()

    public required init?(map: Map) {
        super.init(map: map)
    }

    override public func mapping(map: Map) {
        super.mapping(map: map)

        data <- map["product_types"]
    }
}

public class ProductTypeItem: Mappable, Equatable {
    public var productTypeId: Int!
    public var productTypeName: String?
    public var productTypeNotation: String?

    public required init?(map: Map) {
    }

    public func mapping(map: Map) {

        productTypeId <- map["product_type_id"]
        productTypeName <- map["product_type_name"]
        productTypeNotation <- map["product_type_notation"]
    }

    public static func == (lhs: ProductTypeItem, rhs: ProductTypeItem) -> Bool {
        return lhs.productTypeId == rhs.productTypeId
    }
}
