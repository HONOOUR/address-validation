# address-validation
test program for returning a valid road address name (도로명)

## Overview
### Input case
한글 또는 영문 + (숫자) + 로|길 + (숫자) + 한글 또는 영문

### Behaviour
1. Input enhancement
   <br/> 한글 또는 영문 + (숫자) + 로|길 + (숫자)
2. juso API
   <br/>`https://business.juso.go.kr/addrlink/addrLinkApi.do?` <br/>“rn” 해쉬키에 도로명만 저장하고 있음

### Output
<br/>Success -> road name
<br/>e.g. http://localhost:8080/address/성남, 분당 백 현 로 265, 푸른마을 아파트로 보내주세요!! -> 백현로
<br/>Fail -> Runtime Exception, Not found message
<br/>e.g. http://localhost:8080/address/마포구 도화-23322길 코끼리분식 -> Not Found