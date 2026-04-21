"use client";

import { useEffect, useState } from "react";
import { Button } from "./Button";

export const Contador = () => {
    const [contador, setContador] = useState(0);
    const [coisa, setCoisa] = useState("");

    useEffect(() => {
        console.log("State contador atualizado!");
    }, [contador]);

    useEffect(() => {
        console.log("State coisa atualizado!");
    }, [coisa]);

    useEffect(() => {
        console.log("State contador ou coisa atualizado!");
    }, [contador, coisa]);

    return (
        <div className="grid gap-y-4">
            <h2 className="text-xl">Contador</h2>
            <p>Número atual: {contador}</p>
            <div className="flex gap-x-2">
                <Button onClick={() => {
                            setContador((c) => c - 3);
                        }}>-3</Button>
                <Button onClick={() => {
                            setContador((c) => c - 1);
                        }}>-1</Button>
                <Button onClick={() => {
                            setContador((c) => c + 1);
                        }}>+1</Button>
                <Button onClick={() => {
                            setContador((c) => c + 3);
                        }}>+3</Button>
            </div>
            <div className="flex gap-x-2">
                <input 
                    className="border border-gray-500 px-4 py-1"
                    value={coisa} 
                    onChange={(e) => {
                        setCoisa(e.target.value);
                    }}
                />
                <Button onClick={() => {
                            setCoisa("");
                        }}>Limpar</Button>
            </div>
        </div>
        
    );
}
