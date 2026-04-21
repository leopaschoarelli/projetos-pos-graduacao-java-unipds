import ContadorProvider from "@/src/context/ContadorContext";

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return <ContadorProvider>{children}</ContadorProvider>;
}
