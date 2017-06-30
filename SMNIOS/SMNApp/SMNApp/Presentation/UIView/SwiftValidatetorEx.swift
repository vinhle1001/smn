//
//  SwiftValidatetorEx.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/28/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import SwiftValidator

extension Validator{
    public func clearError(){
        for (_, validation) in validations {
            
            //Reset field
            if let field = validation.field as? UITextField{
                field.layer.borderColor = UIColor.clear.cgColor
                field.layer.borderWidth = 0.0
            }
            
            //reset lable
            if let lable = validation.errorLabel {
                lable.isHidden = true
            }
        }
    }
}
