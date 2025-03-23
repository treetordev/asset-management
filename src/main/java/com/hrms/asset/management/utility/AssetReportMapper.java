// package com.hrms.asset.management.utility;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.modelmapper.ModelMapper;
// import org.springframework.stereotype.Component;


// import com.hrms.asset.management.dao.AssetReport;
// import com.hrms.asset.management.request.AssetReportRequest;
// import com.hrms.asset.management.response.AssetReportResponse;

// @Component
// public class AssetReportMapper {
//  private final ModelMapper modelMapper;

//     public AssetReportMapper(ModelMapper modelMapper) {
//         this.modelMapper = modelMapper;
      
//     }
//     public AssetReport convertToEntity(AssetReportRequest assetRequest) {
//         try {
//             return modelMapper.map(assetRequest, AssetReport.class);
//         } catch (RuntimeException ex) {
//             throw new RuntimeException("Error in converting AssetReportRequest to AssetReport");
//         }
//     }
//     public AssetReportResponse convertToResponse(AssetReport asset) {
//         try {
//             return modelMapper.map(asset, AssetReportResponse.class);
//         } catch (RuntimeException ex) {
//             throw new RuntimeException("Error in converting AssetReport to AssetRequest");
//         }
//     }
//     public List<AssetReportResponse> convertToResponse(List<AssetReport> assets) {
//         try {
//             return assets.stream().map(asset -> modelMapper.map(asset, AssetReportResponse.class)).collect(Collectors.toList());
//         } catch (RuntimeException ex) {
//             throw new RuntimeException("Error in converting AssetRequest to Asset");
//         }
//     }
// }
