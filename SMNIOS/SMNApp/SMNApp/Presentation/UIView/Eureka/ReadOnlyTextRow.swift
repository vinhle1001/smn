//
//  ReadOnlyTextRow.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import UIKit
import Eureka

open class ReadOnlyTextCell: _FieldCell<String>, CellType {

    required public init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }

    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    open override func setup() {
        super.setup()
        textField.autocorrectionType = .default
        textField.autocapitalizationType = .sentences
        textField.keyboardType = .default
        textField.isUserInteractionEnabled = false
    }
}

open class _ReadOnlyTextRow: FieldRow<ReadOnlyTextCell> {
    public required init(tag: String?) {
        super.init(tag: tag)
    }
}

public final class ReadOnlyTextRow: _ReadOnlyTextRow, RowType {
    public required init(tag: String?) {
        super.init(tag: tag)
    }
}
