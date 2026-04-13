"use client";

import { useState } from "react";

export default function FormRegister() {

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassowrd, setShowPassword] = useState(false);

  return (
      <form className="grid gap-y-6">
        <fieldset className="grid">
          <label className="text-[#7b7c7b]" htmlFor="username">Usuário</label>
          <input className="px-2 py-1
                            text-[#7b7c7b] 
                            border border-[#e8e9e9] 
                            focus:border-[#b1b2b2] 
                            hover:border-[#b1b2b2] 
                            outline-none 
                            shadow-md
                            rounded-lg" 
                            id="username" 
                            name="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}/>
        </fieldset>
        <fieldset className="grid">
          <label className="text-[#7b7c7b]" htmlFor="email">Email</label>
          <input  className="px-2 py-1
                            text-[#7b7c7b] 
                            border border-[#e8e9e9] 
                            focus:border-[#b1b2b2] 
                            hover:border-[#b1b2b2] 
                            outline-none 
                            shadow-md
                            rounded-lg"
                            id="email" 
                            name="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)} />
        </fieldset>
        <fieldset className="grid">

          <label className="text-[#7b7c7b]" htmlFor="password">Senha</label>

          <div className="relative flex items-center">
            <input  className="w-full pl-2 pr-9 py-1
                                text-[#7b7c7b] 
                                border border-[#e8e9e9] 
                                focus:border-[#b1b2b2] 
                                hover:border-[#b1b2b2] 
                                outline-none 
                                shadow-md
                                rounded-lg" 
                                id="password" 
                                name="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                type={showPassowrd ? "text" : "password"} />

            <button className="cursor-pointer absolute right-2" type="button" onClick={() => setShowPassword((show) => !show)}>👀</button>
        </div>
        </fieldset>
        <button className="py-2 bg-[#141516] text-white shadow-md rounded-lg cursor-pointer hover:shadow-none">Cadastrar</button>
      </form>
  );
}
