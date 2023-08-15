## Annotation
 - Annotation은 주석과 같음, Class 파일에는 있지만 자바 바이트 코드로 변환되면 사라짐
 - 자바 바이트 코드로 변환되어도 Annotation을 남기고 싶은 경우 `@Retention` 을 활용
   - RetentionPolicy.CLASS(Default), RetentionPolicy.RUNTIME 등.. 
     - SOURCE: 소스 코드에서만 유지(바이트 코드에선 유지되지 않음)
     - CLASS: 바이트 코드에서 유지(runtime에선 유지되지 않음)
     - RUNTIME: runtime에도 유지

## Annotation Processor
 - 컴파일 단계에서 Annotation에 정의된 일련의 프로세스를 동작하게 하는 것
 - `Lombok` 같은 경우, 컴파일 시점에 Annotation Processor를 사용하여 소스 코드의 AST(Abstract Syntax Tree)를 조작함