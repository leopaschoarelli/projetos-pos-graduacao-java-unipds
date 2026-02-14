import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
// SOURCE - Tempo de Compilação. Ex: "@Override"
// RUNTIME - Tempo de Execução.
@Retention(RetentionPolicy.RUNTIME)
public @interface Visible {

}
