import Link from "next/link";

export default function Home() {
  return (
    <div className="grid gap-y-4">
      <h1 className="text-4xl">Bem vindo!</h1>
      <div>
        <p className="font-bold">Telas disponíveis:</p>
        <ul>
          <li>
            <Link href="/">...</Link>
          </li>
          <li>
            <Link href="/">...</Link>
          </li>
          <li>
            <Link href="/">...</Link>
          </li>
        </ul>
      </div>
    </div>
  );
}
