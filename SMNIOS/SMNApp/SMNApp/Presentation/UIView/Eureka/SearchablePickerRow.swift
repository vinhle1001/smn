//
//  SearchablePickerRow.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit
import Eureka

open class SearchablePickerCell<T>: Cell<T>, CellType, UIPickerViewDataSource, UIPickerViewDelegate, UISearchBarDelegate where T: Equatable {

    @IBOutlet public weak var picker: UIPickerView!
    @IBOutlet public weak var searchBar: UISearchBar!

    private var pickerRow: _SearchablePickerRow<T>? { return row as? _SearchablePickerRow<T> }
    private var originOptions: [T]? { return pickerRow?.options }
    private var filterOptions: [T]?

    public required init(style: UITableViewCellStyle, reuseIdentifier: String?) {

        //Create picker
        let pickerView = UIPickerView()
        self.picker = pickerView
        self.picker?.translatesAutoresizingMaskIntoConstraints = false

        //Create search bar
        let searchBar = UISearchBar()
        self.searchBar = searchBar
        self.searchBar.translatesAutoresizingMaskIntoConstraints = false

        super.init(style: style, reuseIdentifier: reuseIdentifier)

        //Add subviews
        self.contentView.addSubview(searchBar)
        self.contentView.addSubview(pickerView)

        //Add constraint
        let views: [String: Any] = ["picker": pickerView, "searchbar": searchBar]

        self.contentView.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "V:|-0-[searchbar][picker]-0-|", options: [], metrics: nil, views: views))
        self.contentView.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "H:|-0-[searchbar]-0-|", options: [], metrics: nil, views: views))
        self.contentView.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "H:|-0-[picker]-0-|", options: [], metrics: nil, views: views))
    }

    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    open override func setup() {
        super.setup()

        accessoryType = .none
        editingAccessoryType = .none

        //set fillter
        filterOptions = originOptions

        picker.delegate = self
        picker.dataSource = self
        searchBar.delegate = self
    }

    deinit {
        picker?.delegate = nil
        picker?.dataSource = nil
        searchBar?.delegate = nil
    }

    open override func update() {
        super.update()
        textLabel?.text = nil
        detailTextLabel?.text = nil

        //update filter
        let searchText = searchBar.text ?? ""
        if (searchText == "") {
            filterOptions = originOptions
        } else {
            let searchText = searchText.lowercased().folding(options: .diacriticInsensitive, locale: .current)

            filterOptions = originOptions?.filter() { option in
                let text = pickerRow?.displayValueFor?(option)?.lowercased().folding(options: .diacriticInsensitive, locale: .current)
                return text?.contains(searchText) ?? false
            }
        }

        //update picker
        picker.reloadAllComponents()
        if let selectedValue = pickerRow?.value, let index = filterOptions?.index(of: selectedValue) {
            self.picker.selectRow(index, inComponent: 0, animated: true)
            self.pickerView(picker, didSelectRow: index, inComponent: 0)
        }
    }

    open func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }

    open func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return filterOptions?.count ?? 0
    }

    open func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return pickerRow?.displayValueFor?(filterOptions?[row])
    }

    open func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if let picker = pickerRow, let options = filterOptions, !options.isEmpty {
            picker.value = options[row]
        }
    }

    //Search bar
    open func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {

        if (searchText == "") {
            filterOptions = originOptions
        } else {
            let searchText = searchText.lowercased().folding(options: .diacriticInsensitive, locale: .current)

            filterOptions = originOptions?.filter() { option in
                let text = pickerRow?.displayValueFor?(option)?.lowercased().folding(options: .diacriticInsensitive, locale: .current)
                return text?.contains(searchText) ?? false
            }
        }

        //reload picker
        picker.reloadAllComponents()
        picker.selectRow(0, inComponent: 0, animated: true)
        pickerView(picker, didSelectRow: 0, inComponent: 0)
    }
}

open class _SearchablePickerRow<T>: Row<SearchablePickerCell<T>> where T: Equatable {

    open var options = [T]()

    required public init(tag: String?) {
        super.init(tag: tag)
    }
}

/// A generic row where the user can pick an option from a picker view
public final class SearchablePickerRow<T>: _SearchablePickerRow<T>, RowType where T: Equatable {

    required public init(tag: String?) {
        super.init(tag: tag)
    }
}
