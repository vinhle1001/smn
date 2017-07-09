//
//  CustomerClient.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/1/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import FrameworkClient

public class CustomerClient: BaseClient {
    public override init() {
        super.init()
        
        self.baseUrl! += CustomerController.Base.rawValue
    }
    
    public func search(text: String, _ callback: @escaping (_ response: ApiResponse<CustomerSearchResponse>) -> Void) {
        
        var body = createBody()
        
        body["text"] = text
        
        execute(uri: CustomerController.Search.rawValue, body: body, callback)
    }
}
