import Link from "next/link";

export default function Home() {
  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <main className="flex flex-col gap-[32px] row-start-2 items-center sm:items-start">
        <ol className="list-inside list-decimal text-sm/6 text-center sm:text-left font-[family-name:var(--font-geist-mono)]">
          <li>
            <Link className="underline" href="/nivel-0">
               - Nível 0
            </Link>
          </li>
          <li>
            <Link className="underline" href="/nivel-1">
               - Nível 1
            </Link>
          </li>
          <li>
            <Link className="underline" href="/nivel-2">
               - Nível 2
            </Link>
          </li>
          <li>
            <Link className="underline" href="/nivel-3/server-side">
              - Nível 3 - Server Side
            </Link>
          </li>
          <li>
            <Link className="underline" href="/nivel-3/client-side">
              - Nível 3 - Client Side
            </Link>
          </li>
          <li>
            <Link className="underline" href="/nivel-3/community-libraries">
              - Nível 3 - Community Libraries
            </Link>
          </li>
          <li>
            <Link className="underline" href="/login">
              - Nível 4 - Login
            </Link>
          </li>
                    <li>
            <Link className="underline" href="/dashboard">
              - Nível 4 - Dashboard
            </Link>
          </li>
          <li>
            <Link className="underline" href="/test-server">
              - Nível 4 - Test Server-side
            </Link>
          </li>
          <li>
            <Link className="underline" href="/test-client">
              - Nível 4 - Test Client-side
            </Link>
          </li>
        </ol>
      </main>
      <footer className="row-start-3 flex gap-[24px] flex-wrap items-center justify-center">
        <p>Fundamentos de Front-End com React - 2026</p>
      </footer>
    </div>
  );
}
