//
//  SearchablePickerInlineRow.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit
import Eureka

open class SearchablePickerInlineCell<T: Equatable>: Cell<T>, CellType {

    required public init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }

    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    open override func setup() {
        super.setup()
        accessoryType = .none
        editingAccessoryType = .none
    }

    open override func update() {
        super.update()
        selectionStyle = row.isDisabled ? .none : .default
    }

    open override func didSelect() {
        super.didSelect()
        row.deselect()
    }
}

// MARK: PickerInlineRow

open class _SearchablePickerInlineRow<T>: Row<SearchablePickerInlineCell<T>>, NoValueDisplayTextConformance where T: Equatable {

    public typealias InlineRow = SearchablePickerRow<T>
    open var options = [T]()
    open var noValueDisplayText: String?
    open var placeholder: String?

    required public init(tag: String?) {
        super.init(tag: tag)
    }
}

/// A generic inline row where the user can pick an option from a picker view which shows and hides itself automatically
public final class SearchablePickerInlineRow<T>: _SearchablePickerInlineRow<T>, RowType, InlineRowType where T: Equatable {

    private var textColor: UIColor?

    required public init(tag: String?) {
        super.init(tag: tag)

        if (onExpandInlineRowCallback == nil) { self.onExpandInlineRowEx({ _, _, _ in }) }
        if (onCollapseInlineRowCallback == nil) { self.onCollapseInlineRowEx({ _, _, _ in }) }
    }

    public override func customDidSelect() {
        super.customDidSelect()
        if !isDisabled {
            toggleInlineRow()
        }
    }

    public func setupInlineRow(_ inlineRow: InlineRow) {
        inlineRow.options = self.options
        inlineRow.displayValueFor = self.displayValueFor
    }

    @discardableResult
    public func onCollapseInlineRowEx(_ callback: @escaping (SearchablePickerInlineCell<T>, SearchablePickerInlineRow<T>, SearchablePickerRow<T>) -> Void) -> SearchablePickerInlineRow<T> {
        onCollapseInlineRow({ (cell, row, inlineRow) -> Void in

            cell.detailTextLabel?.textColor = self.textColor
            callback(cell, row, inlineRow)
        })

        return self
    }

    @discardableResult
    public func onExpandInlineRowEx(_ callback: @escaping (SearchablePickerInlineCell<T>, SearchablePickerInlineRow<T>, SearchablePickerRow<T>) -> Void) -> SearchablePickerInlineRow<T> {
        onExpandInlineRow({ (cell, row, inlineRow) -> Void in

            self.textColor = cell.detailTextLabel?.textColor
            cell.detailTextLabel?.textColor = cell.tintColor
            inlineRow.cell.searchBar.becomeFirstResponder()
            
            callback(cell, row, inlineRow)
        })

        return self
    }
}
