//
//  ResponseHelper.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/12/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit
import FrameworkClient

public class ResponseHelper {
    public static func CheckError<T:BaseResponse>(controller: UIViewController, _ response: ApiResponse<T>) -> Bool {
        //Check client error
        if response.statusCode != 200 {
            AlertEx.ShowAlert(controller: controller, title: "Lỗi", message: response.message)
            return false
        }

        //Check server error
        if response.data == nil {
            AlertEx.ShowAlert(controller: controller, title: "Thông báo", message: "Có lỗi xảy ra")
            return false
        }

        if response.data!.code != 1 {
            AlertEx.ShowAlert(controller: controller, title: "Thông báo", message: response.data!.message ?? "")
            return false
        }
        
        return true
    }
}
