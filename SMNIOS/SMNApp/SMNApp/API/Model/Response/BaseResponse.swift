//
//  BaseLoginResponse.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/26/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import ObjectMapper

public class BaseResponse : Mappable{

    public var code:Int?
    public var message:String?
    
    public required init?(map: Map) {
        
    }
    
    public func mapping(map: Map) {
        code <- map["code"]
        message <- map["message"]
    }
}
