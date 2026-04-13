import FormRegister from "@/components/FormRegister";
import Link from "next/link";

export default function Cadastro() {

  return (
    <div className="grid gap-y-4 px-8 min-w-100 py-12 bg-[#fdfcfc] rounded-3xl shadow-xl">
      <h1 className="text-4xl text-center font-bold">Cadastro</h1>
      <FormRegister />
      <Link className="text-center underline" href="/login">Já tenho cadastro</Link>
    </div>
  );
}
