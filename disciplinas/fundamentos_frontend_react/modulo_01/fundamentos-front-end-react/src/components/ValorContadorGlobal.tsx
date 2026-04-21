"use client";

import { useContext } from "react";
import { ContadorContext } from "../context/ContadorContext";

export const ValorContadorGlobal = () => {
    const { contador, setContador } = useContext(ContadorContext);

    return (
        <div className="grid gap-y-4">
            <p>Valor do meu Contador Global: {contador}</p>
        </div>
    );
}
