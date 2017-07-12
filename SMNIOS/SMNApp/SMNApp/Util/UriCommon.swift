//
//  UriCommon.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/26/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation

public enum UserController: String{
    case Base = "account/"
    case Login = "check-login/"
}

public enum CustomerController: String{
    case Base = "customer/"
    case Search = "search-customer-v2/"
}

public enum ResourceController:String{
    case Base = "resource/"
    case Province = "get-all-province-v2/"
    case District = "get-all-district-v2/"
    case DistrictByProvinceId = "get-district-by-province-id/"
    case Ward = "get-all-ward-v2/"
    case WardByDistrictId = "get-ward-by-district-id/"
    case Agency = "get-all-agency-v2/"
    case ProductType = "get-all-product-type-v2/"
}
