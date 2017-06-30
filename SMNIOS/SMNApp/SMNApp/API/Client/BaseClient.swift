//
//  BaseClient.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/26/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import FrameworkClient
import ObjectMapper

public class BaseClient : BaseApiClient{
    public override init(){
        super.init()
        
        baseUrl = Settings.API_BASE_URL
    }
    
    internal func execute<T:Mappable>(uri: String, body: [String: Any], _ callback: @escaping (_ response: ApiResponse<T>) -> Void){
        execute(uri: uri, body: body, encoder: JsonHttpEncoder(), callback)
    }
    
    internal func execute<T:Mappable>(uri: String, body: [String: Any], header: [String: String], _ callback: @escaping (_ response: ApiResponse<T>) -> Void){
        execute(uri: uri, body: body, header: header, encoder: JsonHttpEncoder(), callback)
    }
    
    internal func createBody() -> [String:Any]{
        var body = [String:Any]()
        
        body["source"] = "IOS"
        body["device_id"] = UIDevice.current.identifierForVendor!.uuidString
        
        return body
    }
}
