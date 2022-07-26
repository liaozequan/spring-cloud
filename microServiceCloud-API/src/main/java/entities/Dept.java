package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)//实现链式存储 .set(deptNo).set(deptName)...
public class Dept implements Serializable {
    private Long deptno;
    private String dname;
    private String db_source; //来自哪个数据库，因为微服务框架可以一个服务对应一个数据库，或同一个信息存储到不同数据库
}
