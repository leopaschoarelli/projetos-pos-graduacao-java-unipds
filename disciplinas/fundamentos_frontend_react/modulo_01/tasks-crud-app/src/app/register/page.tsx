import FormRegister from "@/components/FormRegister";
import Link from "next/link";
import { redirect } from "next/navigation";

export default function Cadastro() {

  const handleRegister = async (_: string, formData: FormData) => {
    "use server";
    
    const username = formData.get("username")?.toString();
    const email    = formData.get("email")?.toString();
    const password = formData.get("password")?.toString();

    if (!username || !email || !password) {
      return "Preencha todos os campos!";
    }

    if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) {
      return "E-mail inválido!";
    }

    if (password.length < 6) {
      return "A senha precisa ter pelo menos 6 caracteres!";
    }

    try {
      const body = {
          username,
          email,
          password,
      };
      
      const res = await fetch('http://localhost:4000/auth/register', {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
          'Content-Type' : 'application/json',
        }
      });

      const {token, message} = await res.json();

      if (!token) {
        return message;
      } else {
        // @todo: adicionar lógica de usuário autenticado
        // redirect("/tasks");
      }
    } catch {
      console.error("handleRegister failed");
      return "Erro no Cadastro!";
    }
  }

  return (
    <div className="grid gap-y-4 px-8 min-w-100 py-12 bg-[#fdfcfc] rounded-3xl shadow-xl">
      <h1 className="text-4xl text-center font-bold">Cadastro</h1>
      <FormRegister action={handleRegister} />
      <Link className="text-center underline" href="/login">Já tenho cadastro</Link>
    </div>
  );
}
