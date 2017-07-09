//
//  UserClient.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/26/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import FrameworkClient

public class UserClient: BaseClient {
    public override init() {
        super.init()
        
        self.baseUrl! += UserController.Base.rawValue
    }
    
    public func login(email: String, password: String, _ callback: @escaping (_ response: ApiResponse<LoginResponse>) -> Void) {
        var body = createBody()
        
        body["email"] = email
        body["password"] = password
        
        execute(uri: UserController.Login.rawValue, body: body, callback)
    }
}
