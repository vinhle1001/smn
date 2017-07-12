//
//  SearchCustomerViewController.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/1/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit

class SearchCustomerViewController: UIViewController {

    @IBOutlet weak var sbCustomer: UISearchBar!
    @IBOutlet weak var tbCustomer: UITableView!
    
    var _customerClient = CustomerClient()

    var _dataSource = [CustomerSearchItem]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        //Register table view
        tbCustomer.delegate = self
        tbCustomer.dataSource = self

        //Register search bar
        sbCustomer.delegate = self
        sbCustomer.enablesReturnKeyAutomatically = false
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let billModifyViewController = segue.destination as? BillModifyViewController, let sender = sender as? UIButton{
            
            let row = _dataSource[sender.tag]
            
            //Set params
            billModifyViewController.customerId = row.customerId
            billModifyViewController.mode = EditMode.Create
            
        }else if let listBillViewController = segue.destination as? ListBillViewController, let sender = sender as? UIButton{
            
            let row = _dataSource[sender.tag]
            
            //Set params
            listBillViewController.customerId = row.customerId
        }
    }
}

//Table view
extension SearchCustomerViewController: UITableViewDelegate, UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return _dataSource.count
    }

    // cell height
    func tableView(_ tableView: UITableView, estimatedHeightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableViewAutomaticDimension
    }

    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let customerCell = tableView.dequeueReusableCell(withIdentifier: "CustomerTableViewCell", for: indexPath) as! CustomerTableViewCell
        
        customerCell.setItem(item: _dataSource[indexPath.row])
        customerCell.setRow(index: indexPath.row)
        
        return customerCell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("Select row")
    }

//    func btnNewBillTouched(_ sender: UIButton) {
//        let row = _dataSource[sender.tag]
//        
//        //Show create bill screen
//        let createBillViewController = self.storyboard?.instantiateViewController(withIdentifier: "BillModifyViewController") as! BillModifyViewController
//        
//        //Set params
//        createBillViewController.customerId = row.customerId
//        createBillViewController.mode = EditMode.Create
//        
//        //Push screen
//        self.navigationController?.pushViewController(createBillViewController, animated: true)
//    }
//    
//    func btnBillHistoryTouched(_ sender: UIButton) {
//        let row = _dataSource[sender.tag]
//        
//        //Show create bill screen
//        let listBillViewController = self.storyboard?.instantiateViewController(withIdentifier: "ListBillViewController") as! ListBillViewController
//        
//        //Set params
//        listBillViewController.customerId = row.customerId
//        
//        //Push screen
//        self.navigationController?.pushViewController(listBillViewController, animated: true)
//    }
}

//Search bar
extension SearchCustomerViewController: UISearchBarDelegate {
    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        searchBar.setShowsCancelButton(true, animated: true)
    }
    
    func searchBarShouldEndEditing(_ searchBar: UISearchBar) -> Bool {
        searchBar.setShowsCancelButton(false, animated: true)
        return true
    }

    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
    }

    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        
        searchBar.resignFirstResponder()
        
        //Show waiting
        WaitingOverlay.show(self)
        
        // Do search
        _customerClient.search(text: searchBar.text ?? ""){ response in
            
           //Hide waiting
            WaitingOverlay.hide(self){
                if (response.statusCode != 200 || response.data == nil) { //Check error client
                    AlertEx.ShowAlert(controller: self, title: "Lỗi", message: response.message)
                } else {
                    self._dataSource = response.data!.data
                    
                    if (self._dataSource.count <= 0){
                        AlertEx.ShowAlert(controller: self, title: "Thông báo", message: "Không tìm thấy dữ liệu")
                    }
                    
                    self.tbCustomer.reloadData()
                }
            }
        }
    }
}

