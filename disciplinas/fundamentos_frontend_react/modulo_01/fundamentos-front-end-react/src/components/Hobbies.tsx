const hobbies = ["Filmes", "Series", "Jogos"];

export const Hobbies = () => {
    const [hobbyPreferido, ...outrosHobbies] = hobbies;

    const novosHobbies = ["Musica", "Dormir"];

    const meusHobbies = [
        ...outrosHobbies,
        ...novosHobbies
    ]

    return (
        <div>
            <p>Estes são os meus Hobbies: </p>
            <ul className="list-disc pl-10">
                <li className="font-bold">{hobbyPreferido}</li>
                {meusHobbies.map((meuHobbie, i) => (
                    <li key={`hobby-${i}`}>{meuHobbie}</li>
                ))}
            </ul>
        </div>
    );
};
