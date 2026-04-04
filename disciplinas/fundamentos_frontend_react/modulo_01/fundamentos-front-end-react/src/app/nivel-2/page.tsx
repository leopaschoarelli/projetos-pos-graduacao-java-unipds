import { ContadorGlobal } from "@/src/components/ContadorGlobal";
import { ValorContadorGlobal } from "@/src/components/ValorContadorGlobal";

const Page = () => (
  <div>
    <h1 className="text-4xl font-bold">Página Nivel 2</h1>
    <ContadorGlobal />
    <ValorContadorGlobal />
  </div>
)

export default Page;
