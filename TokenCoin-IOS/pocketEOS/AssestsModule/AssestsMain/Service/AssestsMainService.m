//
//  AssestsMainService.m
//  pocketEOS
//
//  Created by oraclechain on 2018/1/23.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "AssestsMainService.h"
#import "AccountResult2.h"
#import "Account2.h"
#import "Assests.h"
#import "GetRateResult.h"
#import "Rate.h"

@interface AssestsMainService()


@end


@implementation AssestsMainService

- (GetAccountAssetRequest *)getAccountAssetRequest{
    if (!_getAccountAssetRequest) {
        _getAccountAssetRequest = [[GetAccountAssetRequest alloc] init];
    }
    return _getAccountAssetRequest;
}
- (RichListRequest *)richListRequest{
    if (!_richListRequest) {
        _richListRequest = [[RichListRequest alloc] init];
    }
    return _richListRequest;
}

- (GetRateRequest *)getRateRequest{
    if (!_getRateRequest) {
        _getRateRequest = [[GetRateRequest alloc] init];
    }
    return _getRateRequest;
}

/**
 账号资产详情
 */
- (void)get_account_asset:(CompleteBlock)complete{
    WS(weakSelf);
    
    [self.getAccountAssetRequest postOuterDataSuccess:^(id DAO, id data) {
        if ([data isKindOfClass:[NSDictionary class]]) {
            
            AccountResult2 *result = [AccountResult2 mj_objectWithKeyValues:data];
            
            if (![result.code isEqualToNumber:@0]) {
                [TOASTVIEW showWithText: VALIDATE_STRING(result.message)];
                return ;
            }

            NSMutableArray *dataSource = [[NSMutableArray alloc]init];;

            for( NSDictionary *asset_d in result.data.account_assets ) {
                
                NSString *contract = [asset_d objectForKey:@"contract"];
                NSString *asset_str = [asset_d objectForKey:@"asset"];
                
                NSArray *aArray = [asset_str componentsSeparatedByString:@" "];
                Assests *asset = [[Assests alloc] init];
                asset.contract = contract;
                asset.assestsName = aArray[1];
                asset.assests_avtar = aArray[1];
                asset.assests_balance = VALIDATE_STRING(aArray[0]);
                asset.assests_balance_cny = [NSString stringWithFormat:@"%f",[aArray[0] floatValue] * 50];
                asset.assests_balance_usd = [NSString stringWithFormat:@"%f",[aArray[0] floatValue] * 10];
                asset.assests_price_change_in_24 = [NSString stringWithFormat:@"%d",5];
                asset.assests_market_cap_usd = [NSString stringWithFormat:@"%d",10000];
                asset.assests_market_cap_cny = [NSString stringWithFormat:@"%d",60000];
                asset.assests_price_cny = [NSString stringWithFormat:@"%d",50];
                asset.assests_price_usd = [NSString stringWithFormat:@"%d",10];

                if([aArray[1] isEqualToString:@"EOS"]){
                    asset.assests_avtar = @"eos_avatar";
                }else if([aArray[1] isEqualToString:@"BTC"]){
                    asset.assests_avtar = @"btc_avatar";
                }else if([aArray[1] isEqualToString:@"ETH"]){
                    asset.assests_avtar = @"eth_avatar";
                }else{
                    asset.assests_avtar = @"oct_avatar";
                }

                [dataSource addObject:asset];
            }

            weakSelf.dataSourceArray = dataSource;
            complete(result, YES);
    }
        
    } failure:^(id DAO, NSError *error) {
        NSLog(@"%@", error);
        complete(nil, NO);
    }];
}


/**
 get_rate
 */
- (void)get_rate:(CompleteBlock)complete{
    [self.getRateRequest postOuterDataSuccess:^(id DAO, id data) {
        if ([data isKindOfClass:[NSDictionary class]]) {
            GetRateResult *result = [GetRateResult mj_objectWithKeyValues:data];
            complete(result , YES);
        }
    } failure:^(id DAO, NSError *error) {
        complete(nil , NO);
    }];
}

@end
