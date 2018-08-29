package com.oraclechain.pocketeos.blockchain.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/4/26.
 */

public class PushSuccessBean2 {

    /**
     * code : 0
     * message : ok
     * data : {"transaction_id":"989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e","processed":{"packed_trx_digest":"8dcc39cbb942184ea5a52cf4dd5a384d01975baf62624710a534e56310e8f661","_profiling_us":663,"deferred_transaction_requests":[],"read_locks":[{"scope":"........ehbo5","account":"eosio.token"}],"region_id":0,"action_traces":[{"context_free":false,"console":"transfer","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"eosio.token","_profiling_us":600,"cpu_usage":12017,"data_access":[{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]},{"context_free":false,"console":"","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"lucan222","_profiling_us":2,"cpu_usage":0,"data_access":[]}],"net_usage":280,"cycle_index":1,"kcpu_usage":112,"net_usage_words":35,"id":"989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e","_setup_profiling_us":143,"write_locks":[{"scope":"eosio.token","account":"eosio.token"},{"scope":"lucan222","account":"eosio.token"}],"cpu_usage":114688,"shard_index":0,"status":"executed"}}
     */

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
        /**
         * transaction_id : 989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e
         * processed : {"packed_trx_digest":"8dcc39cbb942184ea5a52cf4dd5a384d01975baf62624710a534e56310e8f661","_profiling_us":663,"deferred_transaction_requests":[],"read_locks":[{"scope":"........ehbo5","account":"eosio.token"}],"region_id":0,"action_traces":[{"context_free":false,"console":"transfer","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"eosio.token","_profiling_us":600,"cpu_usage":12017,"data_access":[{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]},{"context_free":false,"console":"","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"lucan222","_profiling_us":2,"cpu_usage":0,"data_access":[]}],"net_usage":280,"cycle_index":1,"kcpu_usage":112,"net_usage_words":35,"id":"989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e","_setup_profiling_us":143,"write_locks":[{"scope":"eosio.token","account":"eosio.token"},{"scope":"lucan222","account":"eosio.token"}],"cpu_usage":114688,"shard_index":0,"status":"executed"}
         */

        private String transaction_id;
        private ProcessedBean processed;

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public ProcessedBean getProcessed() {
            return processed;
        }

        public void setProcessed(ProcessedBean processed) {
            this.processed = processed;
        }

        public static class ProcessedBean {
            /**
             * packed_trx_digest : 8dcc39cbb942184ea5a52cf4dd5a384d01975baf62624710a534e56310e8f661
             * _profiling_us : 663
             * deferred_transaction_requests : []
             * read_locks : [{"scope":"........ehbo5","account":"eosio.token"}]
             * region_id : 0
             * action_traces : [{"context_free":false,"console":"transfer","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"eosio.token","_profiling_us":600,"cpu_usage":12017,"data_access":[{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]},{"context_free":false,"console":"","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"lucan222","_profiling_us":2,"cpu_usage":0,"data_access":[]}]
             * net_usage : 280
             * cycle_index : 1
             * kcpu_usage : 112
             * net_usage_words : 35
             * id : 989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e
             * _setup_profiling_us : 143
             * write_locks : [{"scope":"eosio.token","account":"eosio.token"},{"scope":"lucan222","account":"eosio.token"}]
             * cpu_usage : 114688
             * shard_index : 0
             * status : executed
             */
            private String id;
            private Receipt receipt;
            private int elapsed;
            private int net_usage;
            private boolean scheduled;
            private List<ActionTracesBean> action_traces;
            private String except;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Receipt getReceipt() {
                return receipt;
            }

            public void setReceipt(Receipt receipt) {
                this.receipt = receipt;
            }

            public int getElapsed() {
                return elapsed;
            }
            
            public void setElapsed(int elapsed) {
                this.elapsed = elapsed;
            }

            public int getNet_usage() {
                return net_usage;
            }
            
            public void setNet_usage(int net_usage) {
                this.net_usage = net_usage;
            }

            public boolean getScheduled() {
                return scheduled;
            }
            
            public void setNet_usage(boolean scheduled) {
                this.scheduled = scheduled;
            }

            public List<ActionTracesBean> getAction_traces() {
                return action_traces;
            }

            public void setAction_traces(List<ActionTracesBean> action_traces) {
                this.action_traces = action_traces;
            }

            public String getExcept() {
                return except;
            }

            public void setExcept(String except) {
                this.except = except;
            }

            public static class Receipt {

                private String status;
                private int cpu_usage_us;
                private int net_usage_us;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public int getCpu_usage_us() {
                    return cpu_usage_us;
                }
                
                public void setCpu_usage_us(int cpu_usage_us) {
                    this.cpu_usage_us = cpu_usage_us;
                }

                public int getNet_usage_us() {
                    return net_usage_us;
                }
                
                public void setNet_usage_us(int net_usage_us) {
                    this.net_usage_us = net_usage_us;
                }


            }

            public static class ActionTracesBean {
                /**
                 * context_free : false
                 * console : transfer
                 * act : {"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"}
                 * receiver : eosio.token
                 * _profiling_us : 600
                 * cpu_usage : 12017
                 * data_access : [{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]
                 */

                private ReceiptAction receipt;
                private ActBean act;
                private int elapsed;
                private int cpu_usage;
                private String console;
                private int total_cpu_usage;
                private String trx_id;
                private List<ActionTracesBean> inline_traces;

                public ReceiptAction getReceipt() {
                    return receipt;
                }

                public void setReceipt(ReceiptAction receipt) {
                    this.receipt = receipt;
                }

                public ActBean getAct() {
                    return act;
                }

                public void setReceipt(ActBean act) {
                    this.act = act;
                }

                public int getElapsed() {
                    return elapsed;
                }
                
                public void setElapsed(int elapsed) {
                    this.elapsed = elapsed;
                }

                public int getCpu_usage() {
                    return cpu_usage;
                }
                
                public void setCpu_usage(int cpu_usage) {
                    this.cpu_usage = cpu_usage;
                }

                public String getConsole() {
                    return console;
                }
                
                public void setConsole(String console) {
                    this.console = console;
                }

                public int getTotal_cpu_usage() {
                    return total_cpu_usage;
                }
                
                public void setTotal_cpu_usage(int total_cpu_usage) {
                    this.total_cpu_usage = total_cpu_usage;
                }

                public String getTrx_id() {
                    return trx_id;
                }
                
                public void setTrx_id(String trx_id) {
                    this.trx_id = trx_id;
                }

                public List<ActionTracesBean> getInline_traces() {
                    return inline_traces;
                }

                public void setInline_traces(List<ActionTracesBean> inline_traces) {
                    this.inline_traces = inline_traces;
                }

                public static class ReceiptAction {
                    private String receiver;
                    private String act_digest;
                    private int global_sequence;
                    private int recv_sequence;
                    private List<Object> auth_sequence;
                    private int code_sequence;
                    private int abi_sequence;

                    public String getReceiver() {
                        return receiver;
                    }   
                    
                    public void setReceiver(String receiver) {
                        this.receiver = receiver;
                    }

                    public String getAct_digest() {
                        return act_digest;
                    }   
                    
                    public void setAct_digest(String act_digest) {
                        this.act_digest = act_digest;
                    }

                    public int getGlobal_sequence() {
                        return global_sequence;
                    }
                    
                    public void setGlobal_sequence(int global_sequence) {
                        this.global_sequence = global_sequence;
                    }

                    public int getRecv_sequence() {
                        return recv_sequence;
                    }
                    
                    public void setRecv_sequence(int recv_sequence) {
                        this.recv_sequence = recv_sequence;
                    }

                    public List<Object> getAuth_sequence() {
                        return auth_sequence;
                    }

                    public void setAuth_sequence(List<Object> auth_sequence) {
                        this.auth_sequence = auth_sequence;
                    }

                    public int getCode_sequence() {
                        return code_sequence;
                    }
                    
                    public void setCode_sequence(int code_sequence) {
                        this.code_sequence = code_sequence;
                    }

                    public int getAbi_sequence() {
                        return abi_sequence;
                    }
                    
                    public void setAbi_sequence(int abi_sequence) {
                        this.abi_sequence = abi_sequence;
                    }


                    public static class AuthSequence {
                        private String account_name;
                        private int auth;

                        public String getAccount_name() {
                            return account_name;
                        }   
                        
                        public void setAccount_name(String account_name) {
                            this.account_name = account_name;
                        }

                        public int getAuth() {
                            return auth;
                        }   
                        
                        public void setAuth(int auth) {
                            this.auth = auth;
                        }


                    }


                }



                public static class ActBean {
                    /**
                     * authorization : [{"actor":"lucan222","permission":"active"}]
                     * hex_data : 000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9
                     * data : {"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"}
                     * name : transfer
                     * account : eosio.token
                     */

                    private String hex_data;
                    private DataBean data;
                    private String name;
                    private String account;
                    private List<AuthorizationBean> authorization;

                    public String getHex_data() {
                        return hex_data;
                    }

                    public void setHex_data(String hex_data) {
                        this.hex_data = hex_data;
                    }

                    public DataBean getData() {
                        return data;
                    }

                    public void setData(DataBean data) {
                        this.data = data;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getAccount() {
                        return account;
                    }

                    public void setAccount(String account) {
                        this.account = account;
                    }

                    public List<AuthorizationBean> getAuthorization() {
                        return authorization;
                    }

                    public void setAuthorization(List<AuthorizationBean> authorization) {
                        this.authorization = authorization;
                    }

                    public static class DataBean {
                        /**
                         * quantity : 6.0000 EOS
                         * memo : 恭喜发财,大吉大利
                         * from : lucan222
                         * to : eosio.token
                         */

                        private String quantity;
                        private String memo;
                        private String from;
                        private String to;

                        public String getQuantity() {
                            return quantity;
                        }

                        public void setQuantity(String quantity) {
                            this.quantity = quantity;
                        }

                        public String getMemo() {
                            return memo;
                        }

                        public void setMemo(String memo) {
                            this.memo = memo;
                        }

                        public String getFrom() {
                            return from;
                        }

                        public void setFrom(String from) {
                            this.from = from;
                        }

                        public String getTo() {
                            return to;
                        }

                        public void setTo(String to) {
                            this.to = to;
                        }
                    }

                    public static class AuthorizationBean {
                        /**
                         * actor : lucan222
                         * permission : active
                         */

                        private String actor;
                        private String permission;

                        public String getActor() {
                            return actor;
                        }

                        public void setActor(String actor) {
                            this.actor = actor;
                        }

                        public String getPermission() {
                            return permission;
                        }

                        public void setPermission(String permission) {
                            this.permission = permission;
                        }
                    }
                }

            }

        }
    }
}
