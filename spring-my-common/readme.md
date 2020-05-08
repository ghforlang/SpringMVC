1、常用工具类
2、异常封装
@ControllerAdvice可以与@InitBinder、@ModelAttribute、@ExceptionHandler配套使用 #see web.readme.md;
主要关注@ExceptionHandler

@ControllerAdvice可以把异常处理器应用到所有处理器，而非单个控制器；借助该注解，可以实现在独立的某个地方，比如单独一个类，
定义一套对各种异常的处理机制，然后在类签名上加上@ControllerAdvice注解，统一对不同阶段、不同异常进行处理，这就是统一异常处理
的原理