package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/2/5.
 */

public class PairTransferHistoryBean {


    private int code;
    private String message;
    private DataBeanX data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        //private List<TransactionsBean> transactions;
        private List<ActionBean> actions;
        private int last_irreversible_block;

        public List<ActionBean> getActions() {
            return actions;
        }
        public void setActions(List<ActionBean> actions) {
            this.actions = actions;
        }

        public int getLast_irreversible_block() {
            return last_irreversible_block;
        }

        public void setLast_irreversible_block(int last_irreversible_block) {
            this.last_irreversible_block = last_irreversible_block;
        }

        public static class ActionBean {
            /**
             * transaction_id : 72a5ca796487458f0187951f1f6b26fb8128bbfb2fffa630ee953042e98a9a67
             * seq_num : 0
             * transaction : {"packed_trx":"d0deeb5a00008f18dfecd799000000000100a6823403ea3055000000572d3ccdcd01000000428869908e00000000a8ed32323a000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","packed_context_free_data":"00","compression":"none","signatures":["EOSKcyq1uQgfSmgP5THRyHnecCMZQj2HRFtRejEfwAvJ83zetcYRozuv2NqApQG9uY6tUXnkoZSttfsMXaDZ4AYP4UC868DkP"],"transaction":{"ref_block_prefix":2581064927,"context_free_actions":[],"expiration":"2018-05-04T04:17:20","max_net_usage_words":0,"max_kcpu_usage":0,"delay_sec":0,"region":0,"ref_block_num":6287,"actions":[{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"1.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"}]},"context_free_data":[]}
             */

            private String transaction_id;
            private int block_num;
            private String block_time;
            private int seq_num;
            private DataBean data;

            public String getTransaction_id() {
                return transaction_id;
            }

            public void setTransaction_id(String transaction_id) {
                this.transaction_id = transaction_id;
            }

            public int getBlock_num() {
                return block_num;
            }

            public void setBlock_num(int block_num) {
                this.block_num = block_num;
            }

            public String getBlock_time() {
                return block_time;
            }

            public void setBlock_time(String block_time) {
                this.block_time = block_time;
            }

            public int getSeq_num() {
                return seq_num;
            }

            public void setSeq_num(int seq_num) {
                this.seq_num = seq_num;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * quantity : 1.0000 EOS
                 * memo : 恭喜发财,大吉大利
                 * from : lucan222
                 * to : eosio.token
                 */
                private String from;
                private String quant;
                private String symbol;
                private String feeto;
                private String pair;

                public String getFrom() {
                    return from;
                }

                public void setFrom(String from) {
                    this.from = from;
                }

                public String getQuant() {
                    return quant;
                }

                public void setQuant(String quant) {
                    this.quant = quant;
                }

                public String getSymbol() {
                    return symbol;
                }

                public void setSymbol(String symbol) {
                    this.symbol = symbol;
                }

                public String getFeeto() {
                    return feeto;
                }
                public void setFeeto(String feeto) {
                    this.feeto = feeto;
                }

                public String getPair() {
                    return pair;
                }
                public void setPair(String pair) {
                    this.pair = pair;
                }
            }

        }
    }
}
