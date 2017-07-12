//
//  SplashViewController.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit

class SplashViewController: UIViewController {

    @IBOutlet weak var progressView: UIProgressView!

    private let resourceClient = ResourceClient()

    override func viewDidLoad() {
        super.viewDidLoad()

        loadData(progressChange: { progress in
            //Change progress
            self.progressView.progress = progress
        }, complete: {
            //Show main screen
            let loginViewController = self.storyboard?.instantiateViewController(withIdentifier: "LoginViewController") as! LoginViewController
            self.navigationController?.pushViewController(loginViewController, animated: true)
        })
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    private func loadData(progressChange: @escaping (Float) -> Void, complete: @escaping () -> Void) {
        //Set progress to zero
        progressChange(0)

        //Load data
        self.loadProvinces {

            progressChange(33)
            self.loadAgencys {

                progressChange(66)
                self.loadProductTypes {

                    progressChange(100)
                    complete()
                }
            }
        }
    }

    //load province
    private func loadProvinces(_ complete: @escaping () -> Void) {
        resourceClient.provides() { response in
            if (response.statusCode != 200 || response.data == nil) { //Check error client

                AlertEx.ShowAlert(controller: self, title: "Error", message: response.message) {
                    exit(0)
                }
            } else if (response.data!.code != 1) { //Check server error

                AlertEx.ShowAlert(controller: self, title: "Error", message: response.data?.message ?? "") {
                    exit(0)
                }
            } else { // success
                StaticVariables.provices = response.data?.data

            }

            complete()
        }
    }

    //load
    private func loadProductTypes(_ complete: @escaping () -> Void) {
        resourceClient.productTypes() { response in
            if (response.statusCode != 200 || response.data == nil) { //Check error client

                AlertEx.ShowAlert(controller: self, title: "Error", message: response.message) {
                    exit(0)
                }
            } else if (response.data!.code != 1) { //Check server error

                AlertEx.ShowAlert(controller: self, title: "Error", message: response.data?.message ?? "") {
                    exit(0)
                }
            } else { // success
                StaticVariables.productTypes = response.data?.data

            }

            complete()
        }
    }

    private func loadAgencys(_ complete: @escaping () -> Void) {
        resourceClient.agencies() { response in
            if (response.statusCode != 200 || response.data == nil) { //Check error client

                AlertEx.ShowAlert(controller: self, title: "Lỗi", message: response.message, nil) {_ in
                    exit(0)
                }
            } else if (response.data!.code != 1) { //Check server error

                AlertEx.ShowAlert(controller: self, title: "Lỗi", message: response.data?.message ?? "", nil) {_ in
                    exit(0)
                }
            } else { // success
                StaticVariables.agencies = response.data?.data

            }

            complete()
        }
    }
}
