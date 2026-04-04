import { FC } from "react";

export const MeuNome: FC<{name: string, age: number, birthDate: Date}> = (props) => {
    return (
        <>
        <p>Sou o {props.name}, tenho {props.age} anos porque nasci no dia {props.birthDate.toLocaleDateString("pt-BR")}</p>
        </>
    );
};
