//
//  LoginResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/26/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class LoginResponse: BaseResponse {
    public var accountId:Int!
    public var accountEmail:String!
    public var accountFullName:String!
    public var accountSession:String!
    
    public required init?(map: Map) {
        super.init(map: map)
    }
    
    override public func mapping(map: Map) {
        super.mapping(map: map)
        
        accountId <- map["account_id"]
        accountEmail <- map["account_email"]
        accountFullName <- map["account_full_name"]
        accountSession <- map["account_session"]
    }
}
