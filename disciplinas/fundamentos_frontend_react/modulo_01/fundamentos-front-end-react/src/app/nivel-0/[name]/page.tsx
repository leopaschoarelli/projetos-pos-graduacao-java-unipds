// export default function Page() {
//   return <div>Page</div>
// }

import { Hobbies } from "@/src/components/Hobbies";
import { Imagem } from "@/src/components/Imagem";
import { MeuNome } from "@/src/components/MeuNome";
import Link from "next/link";

type PageProps = {
  params: Promise<{
    name: string;
  }>;
};

const Page = async ({ params }: PageProps) => {

  const { name } = await params;

  return (
    <div className="grid gap-y-4">
      <MeuNome name={name} age={31} birthDate={new Date(1995, 2, 10)}/>
      <Hobbies />
      <div>
        <p>Gosto de: </p>
        <Imagem />
      </div>
      <Link className="underline" href="/nivel-0">
        Voltar
      </Link>
    </div>
  );
};

export default Page;
