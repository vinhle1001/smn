//
//  WaitingOverlayController.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/27/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit

public class WaitingOverlay{
    public static func show(_ controller: UIViewController){
        let alert = UIAlertController(title: nil, message: "Please wait...", preferredStyle: .alert)
        
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
        loadingIndicator.startAnimating();
        
        alert.view.addSubview(loadingIndicator)
        controller.present(alert, animated: true, completion: nil)
    }
    
    public static func hide(_ controller: UIViewController,_ completion: (() -> Swift.Void)? = nil){
        controller.dismiss(animated: false, completion: completion)
    }
}
