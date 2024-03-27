# store99-bookstore

### DEV 환경

| VM Option                 | Value                                                                                       |
|---------------------------|---------------------------------------------------------------------------------------------|
| spring.profiles.active    | dev                                                                                         |
| skm.authentication-id     | User Access Key ID                                                                          |
| skm.authentication-secret | Secret Access Key                                                                           |
| skm.certificate-password  | Certificate Password                                                                        |
| skm.app-key               | Appkey in "URL & Appkey - Secure Key Manager"                                               |
| db.url                    | jdbc:mysql://{ip}:{port}/{database}?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8 |
| db.username               | Personal Database Username                                                                  |
| db.password               | Personal Database Password                                                                  |

### PROD 환경

| VM Option                 | Value                                         |
|---------------------------|-----------------------------------------------|
| spring.profiles.active    | prod                                          |
| LOG_N_CRASH_APP_KEY       | Appkey in "URL & Appkey - Log & Crash Search" |
| skm.authentication-id     | User Access Key ID                            |
| skm.authentication-secret | Secret Access Key                             |
| skm.certificate-password  | Certificate Password                          |
| skm.app-key               | Appkey in "URL & Appkey - Secure Key Manager" |