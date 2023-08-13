## Reflection

## Annotation
 - Annotation은 주석과 같음, Class 파일에는 있지만 자바 바이트 코드로 변환되면 사라짐
 - 자바 바이트 코드로 변환되어도 Annotation을 남기고 싶은 경우 `@Retention` 을 활용
   - RetentionPolicy.CLASS(Default), RetentionPolicy.RUNTIME 등.. 