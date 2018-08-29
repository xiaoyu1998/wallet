//
//  TransactionRecordsService.h
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "BaseService.h"
#import "GetTransactionRecordsRequest.h"

@interface TransactionRecordsService2 : BaseService

@property(nonatomic, strong) NSMutableArray *tokenTransactionDatasourceArray;
@property(nonatomic, strong) NSMutableArray *sendTransactionDatasourceArray;
@property(nonatomic, strong) NSMutableArray *recieveTransactionDatasourceArray;
@property(nonatomic, strong) GetTransactionRecordsRequest *getTransactionRecordsRequest;
@property(nonatomic, strong) NSString *currentSymbolName;
- (void)buildNextPageDataSource:(CompleteBlock)complete;
@end
