"use client";

import { useContext } from "react";
import { ContadorContext } from "../context/ContadorContext";
import { Button } from "./Button";

export const ContadorGlobal = () => {
    const { contador, setContador } = useContext(ContadorContext);

    return (
        <div className="grid gap-y-4">
            <h2 className="text-xl">Contador Global</h2>
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
      </div>
    );
}