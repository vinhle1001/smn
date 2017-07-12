//
//  ResourceClient.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 7/8/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation
import FrameworkClient

public class ResourceClient: BaseClient {
    public override init() {
        super.init()
        
        self.baseUrl! += ResourceController.Base.rawValue
    }
    
    public func provides(_ callback: @escaping (_ response: ApiResponse<ProvinceResponse>) -> Void){
        execute(uri: ResourceController.Province.rawValue, body: createBody(), callback)
    }
    
    public func districts(_ callback: @escaping (_ response: ApiResponse<DistrictResponse>) -> Void){
        execute(uri: ResourceController.District.rawValue, body: createBody(), callback)
    }
    
    public func wards(_ callback: @escaping (_ response: ApiResponse<WardResponse>) -> Void){
        execute(uri: ResourceController.Ward.rawValue, body: createBody(), callback)
    }
    
    public func agencies(_ callback: @escaping (_ response: ApiResponse<AgencyResponse>) -> Void){
        execute(uri: ResourceController.Agency.rawValue, body: createBody(), callback)
    }
    
    public func productTypes(_ callback: @escaping (_ response: ApiResponse<ProductTypeResponse>) -> Void){
        execute(uri: ResourceController.ProductType.rawValue, body: createBody(), callback)
    }
    
    public func districts(provinceId:Int, _ callback: @escaping (_ response: ApiResponse<DistrictResponse>) -> Void){
        
        var body = createBody()
        
        body["id"] = provinceId
        
        execute(uri: ResourceController.DistrictByProvinceId.rawValue, body: body, callback)
    }
    
    public func wards(districtId:Int, _ callback: @escaping (_ response: ApiResponse<WardResponse>) -> Void){
        
        var body = createBody()
        
        body["id"] = districtId
        
        execute(uri: ResourceController.WardByDistrictId.rawValue, body: body, callback)
    }
}
