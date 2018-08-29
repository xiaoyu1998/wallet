//
//  TransactionRecordsService.m
//  pocketEOS
//
//  Created by oraclechain on 2018/2/7.
//  Copyright © 2018年 oraclechain. All rights reserved.
//

#import "TransactionRecordsService2.h"
#import "TransactionRecord2.h"
#import "TransactionRecordsResult.h"
#import "TransactionsResult2.h"

@interface TransactionRecordsService2()
@property(nonatomic, strong) NSMutableArray *tokenTransactionResponseArray;
@property(nonatomic, strong) NSMutableArray *sendTransactionResponseArray;
@property(nonatomic, strong) NSMutableArray *recieveTransactionResponseArray;

@end


@implementation TransactionRecordsService2
- (GetTransactionRecordsRequest *)getTransactionRecordsRequest{
    if (!_getTransactionRecordsRequest) {
        _getTransactionRecordsRequest = [[GetTransactionRecordsRequest alloc] init];
    }
    return _getTransactionRecordsRequest;
}

- (NSMutableArray *)tokenTransactionDatasourceArray{
    if (!_tokenTransactionDatasourceArray) {
        _tokenTransactionDatasourceArray = [[NSMutableArray alloc] init];
    }
    return _tokenTransactionDatasourceArray;
}

// - (NSMutableArray *)octTransactionDatasourceArray{
//     if (!_octTransactionDatasourceArray) {
//         _octTransactionDatasourceArray = [[NSMutableArray alloc] init];
//     }
//     return _octTransactionDatasourceArray;
// }

// - (NSMutableArray *)redPacketDatasourceArray{
//     if (!_redPacketDatasourceArray) {
//         _redPacketDatasourceArray = [[NSMutableArray alloc] init];
//     }
//     return _redPacketDatasourceArray;
// }
- (NSMutableArray *)sendTransactionDatasourceArray{
    if (!_sendTransactionDatasourceArray) {
        _sendTransactionDatasourceArray = [[NSMutableArray alloc] init];
    }
    return _sendTransactionDatasourceArray;
}

- (NSMutableArray *)recieveTransactionDatasourceArray{
    if (!_recieveTransactionDatasourceArray) {
        _recieveTransactionDatasourceArray = [[NSMutableArray alloc] init];
    }
    return _recieveTransactionDatasourceArray;
}

- (NSMutableArray *)tokenTransactionResponseArray{
    if (!_tokenTransactionResponseArray) {
        _tokenTransactionResponseArray = [[NSMutableArray alloc] init];
    }
    return _tokenTransactionResponseArray;
}

// - (NSMutableArray *)octTransactionResponseArray{
//     if (!_octTransactionResponseArray) {
//         _octTransactionResponseArray = [[NSMutableArray alloc] init];
//     }
//     return _octTransactionResponseArray;
// }

// - (NSMutableArray *)redPacketTransactionResponseArray{
//     if (!_redPacketTransactionResponseArray) {
//         _redPacketTransactionResponseArray = [[NSMutableArray alloc] init];
//     }
//     return _redPacketTransactionResponseArray;
// }

- (NSMutableArray *)sendTransactionResponseArray{
    if (!_sendTransactionResponseArray) {
        _sendTransactionResponseArray = [[NSMutableArray alloc] init];
    }
    return _sendTransactionResponseArray;
}

- (NSMutableArray *)recieveTransactionResponseArray{
    if (!_recieveTransactionResponseArray) {
        _recieveTransactionResponseArray = [[NSMutableArray alloc] init];
    }
    return _recieveTransactionResponseArray;
}

- (void)buildDataSource:(CompleteBlock)complete{
    //complete(@0, YES);
   WS(weakSelf);

   // self.getTransactionRecordsRequest.skip_seq = @(0);
   // self.getTransactionRecordsRequest.num_seq = @(PER_PAGE_SIZE_15);
   self.getTransactionRecordsRequest.skip_seq = @(-20);
   self.getTransactionRecordsRequest.num_seq = @(-1);
   [self.getTransactionRecordsRequest postOuterDataSuccess:^(id DAO, id data) {
       NSLog(@"%@", data);
       [weakSelf.dataSourceArray removeAllObjects];
       [weakSelf.responseArray removeAllObjects];
       [weakSelf.tokenTransactionResponseArray removeAllObjects];
       [weakSelf.tokenTransactionDatasourceArray removeAllObjects];
       // [weakSelf.octTransactionResponseArray removeAllObjects];
       // [weakSelf.octTransactionDatasourceArray removeAllObjects];
       // [weakSelf.redPacketTransactionResponseArray removeAllObjects];
       // [weakSelf.redPacketDatasourceArray removeAllObjects];
       [weakSelf.sendTransactionResponseArray removeAllObjects];
       [weakSelf.sendTransactionDatasourceArray removeAllObjects];
       [weakSelf.recieveTransactionDatasourceArray removeAllObjects];
       [weakSelf.recieveTransactionResponseArray removeAllObjects];

       TransactionRecordsResult *result = [TransactionRecordsResult mj_objectWithKeyValues:data];
       if (![result.code isEqualToNumber:@0]) {
           [TOASTVIEW showWithText: VALIDATE_STRING(result.message)];
       }else{
           TransactionsResult2 *transactionsResult = [TransactionsResult2 mj_objectWithKeyValues:result.data];

           transactionsResult.actions = (NSMutableArray *)[[transactionsResult.actions reverseObjectEnumerator] allObjects];

           for (NSDictionary *record2 in transactionsResult.actions) {

            TransactionRecord2 *record = [TransactionRecord2 mj_objectWithKeyValues:record2];
               
//            TransactionRecord2 *record = [[TransactionRecord2 alloc] init];
//            record.transcation_id = [record2 objectForKey:@"transcation_id"];
//            record.block_time = [record2 objectForKey:@"block_time"];
//            record.block_num = [record2 objectForKey:@"block_num"];
//            record.seq_num = [record2 objectForKey:@"seq_num"];
//
//            NSDictionary *data = [record2 objectForKey:@"data"];
//            record.quantity = [data objectForKey:@"quantity"];
//            record.from     = [data objectForKey:@"from"];
//            record.to       = [data objectForKey:@"to"];
//            record.memo     = [data objectForKey:@"memo"];

               if ([record.quantity containsString:@" "]) {
                   NSArray*quantityArr = [record.quantity  componentsSeparatedByString:@" "];
                   record.amount  = quantityArr[0];
                   record.assestsType = quantityArr[1];
                   if (![record.assestsType isEqualToString:self.currentSymbolName]) continue;
               }
               
               //transfer
               //if ([record.transactionType isEqualToString:@"transfer"]) {
                   [weakSelf.responseArray addObject:record];
                   // if ([record.assestsType isEqualToString:@"EOS"]){
                   //     [weakSelf.eosTransactionResponseArray addObject:record];
                   // }else if ([record.assestsType isEqualToString:@"OCT"]){
                   //     [weakSelf.octTransactionResponseArray addObject:record];
                   // }

                   [weakSelf.tokenTransactionResponseArray addObject:record];

                   // redpacket
                   // if ([record.from isEqualToString:@"oc.redpacket"] || [record.to isEqualToString:@"oc.redpacket"]) {
                   //     [weakSelf.redPacketTransactionResponseArray addObject:record];
                   // }

                   // send
                   if ([record.from isEqualToString:self.getTransactionRecordsRequest.account_name]) {
                        [weakSelf.sendTransactionResponseArray addObject:record];
                   }

                   // recieve
                   if ([record.to isEqualToString:self.getTransactionRecordsRequest.account_name]) {
                        [weakSelf.recieveTransactionResponseArray addObject:record];
                   }
               //}
           }
           weakSelf.dataSourceArray = [NSMutableArray arrayWithArray:weakSelf.responseArray];
           weakSelf.tokenTransactionDatasourceArray = [NSMutableArray arrayWithArray:weakSelf.tokenTransactionResponseArray];
           // weakSelf.octTransactionDatasourceArray = [NSMutableArray arrayWithArray:weakSelf.octTransactionResponseArray];
           // weakSelf.redPacketDatasourceArray = [NSMutableArray arrayWithArray:weakSelf.redPacketTransactionResponseArray];
           weakSelf.sendTransactionDatasourceArray = [NSMutableArray arrayWithArray:weakSelf.sendTransactionResponseArray];
           weakSelf.recieveTransactionDatasourceArray = [NSMutableArray arrayWithArray:weakSelf.recieveTransactionResponseArray];
       }
       complete(@(weakSelf.dataSourceArray.count) , YES);
   } failure:^(id DAO, NSError *error) {
       complete(nil, NO);
   }];
}
//
- (void)buildNextPageDataSource:(CompleteBlock)complete{
    complete(@0, YES);
//    WS(weakSelf);
//    self.getTransactionRecordsRequest.skip_seq = @(self.dataSourceArray.count);
//    self.getTransactionRecordsRequest.num_seq = @(PER_PAGE_SIZE_15);
//    [self.getTransactionRecordsRequest postOuterDataSuccess:^(id DAO, id data) {
//        NSLog(@"%@", data);
//        [weakSelf.responseArray removeAllObjects];
//        [weakSelf.eosTransactionResponseArray removeAllObjects];
//        [weakSelf.octTransactionResponseArray removeAllObjects];
//        [weakSelf.sendTransactionResponseArray removeAllObjects];
//        [weakSelf.recieveTransactionResponseArray removeAllObjects];
//        TransactionRecordsResult *result = [TransactionRecordsResult mj_objectWithKeyValues:data];
//        if (![result.code isEqualToNumber:@0]) {
//            [TOASTVIEW showWithText: VALIDATE_STRING(result.message)];
//        }else{
//            TransactionsResult *transactionsResult = [TransactionsResult mj_objectWithKeyValues:result.data];
//            for (TransactionRecord *record in transactionsResult.transactions) {
//                if ([record.quantity containsString:@" "]) {
//                    NSArray*quantityArr = [record.quantity componentsSeparatedByString:@" "];
//                    record.amount  = quantityArr[0];
//                    record.assestsType = quantityArr[1];
//                }
//
//                if ([record.transactionType isEqualToString:@"transfer"]) {
//                    [weakSelf.responseArray addObject:record];
//                    if ([record.assestsType isEqualToString:@"EOS"]){
//                        [weakSelf.eosTransactionResponseArray addObject:record];
//                    }else if ([record.assestsType isEqualToString:@"OCT"]){
//                        [weakSelf.octTransactionResponseArray addObject:record];
//                    }
//
//                    // send
//                    if ([record.from isEqualToString:weakSelf.getTransactionRecordsRequest.account_name]) {
//                        [weakSelf.sendTransactionResponseArray addObject:record];
//                    }
//
//                    // recieve
//                    if ([record.to isEqualToString:weakSelf.getTransactionRecordsRequest.account_name]) {
//                        [weakSelf.recieveTransactionResponseArray addObject:record];
//                    }
//
//                }
//            }
//
//            [weakSelf.dataSourceArray addObjectsFromArray:weakSelf.responseArray];
//            [weakSelf.eosTransactionDatasourceArray addObjectsFromArray:weakSelf.eosTransactionResponseArray];
//            [weakSelf.octTransactionDatasourceArray addObjectsFromArray:weakSelf.octTransactionResponseArray];
//            [weakSelf.sendTransactionDatasourceArray addObjectsFromArray:weakSelf.sendTransactionResponseArray];
//            [weakSelf.recieveTransactionDatasourceArray addObjectsFromArray:weakSelf.recieveTransactionResponseArray];
//
//
//        }
//        complete(@(weakSelf.responseArray.count) , YES);
//    } failure:^(id DAO, NSError *error) {
//        complete(nil, NO);
//    }];
}
@end
