//
//  AlertEx.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/1/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit

public class AlertEx {
    public static func ShowAlert(controller: UIViewController, title: String, message: String, button: String = "OK", animated: Bool = true, _ completion: (() -> Void)? = nil) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: UIAlertControllerStyle.alert)
        alert.addAction(UIAlertAction(title: button, style: UIAlertActionStyle.default, handler: nil))
        controller.present(alert, animated: animated, completion: completion)
    }

    public static func ShowAlert(controller: UIViewController, title: String, message: String, button: String = "OK", animated: Bool = true, _ completion: (() -> Void)? = nil, _ okHandler: ((UIAlertAction) -> Void)?) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: UIAlertControllerStyle.alert)
        alert.addAction(UIAlertAction(title: button, style: UIAlertActionStyle.default, handler: okHandler))
        controller.present(alert, animated: animated, completion: completion)
    }
}
