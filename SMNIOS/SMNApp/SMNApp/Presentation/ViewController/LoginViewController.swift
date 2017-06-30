//
//  LoginViewController.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/27/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit
import FrameworkClient
import SwiftValidator

class LoginViewController: UIViewController, ValidationDelegate {

    private let _userClient = UserClient()
    private let _validator = Validator()

    @IBOutlet weak var tbxUsername: UITextField!
    @IBOutlet weak var tbxPassword: UITextField!

    @IBOutlet weak var lbLoginError: UILabel!
    @IBOutlet weak var lbPaswordError: UILabel!
    @IBOutlet weak var lbUserError: UILabel!
    
    private var originViewHeight:CGFloat = 0.0

    override func viewDidLoad() {
        super.viewDidLoad()
        
        originViewHeight = self.view.bounds.size.height
        
        //Register validator
        _validator.registerField(tbxUsername, errorLabel: lbUserError, rules: [RequiredRule(message: "Hãy nhập email"), EmailRule(message: "Email chưa đúng")])
        _validator.registerField(tbxPassword, errorLabel: lbPaswordError, rules: [RequiredRule(message: "Hãy nhập mật khẩu"), MinLengthRule(length: 6, message: "Mật khẩu dài ít %ld ký tự")])
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }

    @IBAction func loginPressed(_ sender: Any) {

        //Hide keyboard
        self.tbxUsername.resignFirstResponder()
        self.tbxPassword.resignFirstResponder()
        
        //Reset validate
        self._validator.clearError()
        self.lbLoginError.isHidden = true
        
        //Validate
        _validator.validate(self)

    }

    //Validator
    func validationSuccessful() {
        
        //Hide error lable
        lbUserError.isHidden = true;
        lbPaswordError.isHidden = true;

        //Show waiting
        WaitingOverlay.show(self)

        //Do login
        _userClient.login(email: tbxUsername.text ?? "", password: tbxPassword.text ?? "") { response in

            //Hide Waiting
            WaitingOverlay.hide(self) {

                //Login
                if (response.statusCode != 200 || response.data == nil) { //Check error client

                    self.lbLoginError.text = response.message;
                    self.lbLoginError.isHidden = false;

                } else if (response.data!.code != 1) { //Check server error

                    self.lbLoginError.text = response.data!.message;
                    self.lbLoginError.isHidden = false;

                } else { //Login success

                    //Hide error lable
                    self.lbLoginError.isHidden = true;

                    //Show main screen
                    let mainViewController = self.storyboard?.instantiateViewController(withIdentifier: "mainViewController") as! MainViewController
                    self.navigationController?.pushViewController(mainViewController, animated: true)
                }
            }
        }
    }

    func validationFailed(_ errors: [(Validatable, ValidationError)]) {
        // turn the fields to red
        for (field, error) in errors {
            if let field = field as? UITextField {
                field.layer.borderColor = UIColor.red.cgColor
                field.layer.borderWidth = 1.0
            }
            error.errorLabel?.text = error.errorMessage // works if you added labels
            error.errorLabel?.isHidden = false
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
