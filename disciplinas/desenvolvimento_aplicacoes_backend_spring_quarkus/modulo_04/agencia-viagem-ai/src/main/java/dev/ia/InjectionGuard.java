package dev.ia;

import io.quarkiverse.langchain4j.guardrails.ToolInputGuardrail;
import io.quarkiverse.langchain4j.guardrails.ToolInputGuardrailRequest;
import io.quarkiverse.langchain4j.guardrails.ToolInputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InjectionGuard implements ToolInputGuardrail {

    @Inject
    PromptSecurityExpert securityExpert;

    @Override
    public ToolInputGuardrailResult validate(ToolInputGuardrailRequest request) {
        String arguments = request.arguments();

        if (securityExpert.isAttack(arguments)) {
            return ToolInputGuardrailResult.failure(
                    "Sua mensagem foi bloqueada por conter instruções não permitidas."
            );
        }

        return ToolInputGuardrailResult.success();
    }
}
