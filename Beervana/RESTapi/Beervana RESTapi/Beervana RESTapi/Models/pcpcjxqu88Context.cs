using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace Beervana_RESTapi.Models
{
    public partial class pcpcjxqu88Context : DbContext
    {
        public pcpcjxqu88Context()
        {
        }

        public pcpcjxqu88Context(DbContextOptions<pcpcjxqu88Context> options)
            : base(options)
        {
        }

        public virtual DbSet<Clanstvo> Clanstvo { get; set; }
        public virtual DbSet<DegustacijskiMeni> DegustacijskiMeni { get; set; }
        public virtual DbSet<Dogadaj> Dogadaj { get; set; }
        public virtual DbSet<DogadajNaLokaciji> DogadajNaLokaciji { get; set; }
        public virtual DbSet<Kategorija> Kategorija { get; set; }
        public virtual DbSet<Korisnik> Korisnik { get; set; }
        public virtual DbSet<Lokacija> Lokacija { get; set; }
        public virtual DbSet<Obilazak> Obilazak { get; set; }
        public virtual DbSet<OmiljenaLokacija> OmiljenaLokacija { get; set; }
        public virtual DbSet<OmiljenoPivo> OmiljenoPivo { get; set; }
        public virtual DbSet<Proizvod> Proizvod { get; set; }
        public virtual DbSet<ProizvodNaPromociji> ProizvodNaPromociji { get; set; }
        public virtual DbSet<Promocija> Promocija { get; set; }
        public virtual DbSet<PromocijaNaLokaciji> PromocijaNaLokaciji { get; set; }
        public virtual DbSet<Recenzija> Recenzija { get; set; }
        public virtual DbSet<StavkaDegustacijskogMenija> StavkaDegustacijskogMenija { get; set; }
        public virtual DbSet<Uloga> Uloga { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseMySQL("server=localhost;port=3306;user=root;database=pcpcjxqu88");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Clanstvo>(entity =>
            {
                entity.HasKey(e => e.IdClanstvo)
                    .HasName("PRIMARY");

                entity.ToTable("clanstvo");

                entity.Property(e => e.IdClanstvo)
                    .HasColumnName("id_clanstvo")
                    .HasColumnType("int(11)");

                entity.Property(e => e.CijenaClanstva).HasColumnName("cijena_clanstva");

                entity.Property(e => e.VrijemeTrajanja)
                    .HasColumnName("vrijeme_trajanja")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.VrstaClanstva)
                    .IsRequired()
                    .HasColumnName("vrsta_clanstva")
                    .HasMaxLength(45)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<DegustacijskiMeni>(entity =>
            {
                entity.HasKey(e => e.IdDegustacijskiMeni)
                    .HasName("PRIMARY");

                entity.ToTable("degustacijski_meni");

                entity.HasIndex(e => e.IdKorisnik)
                    .HasName("fk_degustacijski_meni_korisnik1_idx");

                entity.HasIndex(e => e.IdLokacija)
                    .HasName("fk_degustacijski_meni_lokacija1_idx");

                entity.Property(e => e.IdDegustacijskiMeni)
                    .HasColumnName("id_degustacijski_meni")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DatumKreiranja).HasColumnName("datum_kreiranja");

                entity.Property(e => e.IdKorisnik)
                    .HasColumnName("id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.IdLokacija)
                    .HasColumnName("id_lokacija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NazivMenija)
                    .IsRequired()
                    .HasColumnName("naziv_menija")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.OpisMenija)
                    .HasColumnName("opis_menija")
                    .HasColumnType("longtext")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.SlikaMenija)
                    .HasColumnName("slika_menija")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Trajanje)
                    .HasColumnName("trajanje")
                    .HasColumnType("int(11)");

                entity.HasOne(d => d.IdKorisnikNavigation)
                    .WithMany(p => p.DegustacijskiMeni)
                    .HasForeignKey(d => d.IdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_degustacijski_meni_korisnik1");

                entity.HasOne(d => d.IdLokacijaNavigation)
                    .WithMany(p => p.DegustacijskiMeni)
                    .HasForeignKey(d => d.IdLokacija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_degustacijski_meni_lokacija1");
            });

            modelBuilder.Entity<Dogadaj>(entity =>
            {
                entity.HasKey(e => e.IdDogadaj)
                    .HasName("PRIMARY");

                entity.ToTable("dogadaj");

                entity.HasIndex(e => e.IdKorisnik)
                    .HasName("fk_dogadaj_korisnik1_idx");

                entity.Property(e => e.IdDogadaj)
                    .HasColumnName("id_dogadaj")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DatumKreiranja).HasColumnName("datum_kreiranja");

                entity.Property(e => e.IdKorisnik)
                    .HasColumnName("id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NazivDogadaja)
                    .IsRequired()
                    .HasColumnName("naziv_dogadaja")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.OpisDogađaja)
                    .HasColumnName("opis_događaja")
                    .HasColumnType("longtext")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.VizualDogađaja)
                    .HasColumnName("vizual_događaja")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.HasOne(d => d.IdKorisnikNavigation)
                    .WithMany(p => p.Dogadaj)
                    .HasForeignKey(d => d.IdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_dogadaj_korisnik1");
            });

            modelBuilder.Entity<DogadajNaLokaciji>(entity =>
            {
                entity.HasKey(e => new { e.LokacijaIdLokacija, e.DogadajIdDogadaj })
                    .HasName("PRIMARY");

                entity.ToTable("dogadaj_na_lokaciji");

                entity.HasIndex(e => e.DogadajIdDogadaj)
                    .HasName("fk_dogadaj_na_lokaciji_dogadaj1_idx");

                entity.Property(e => e.LokacijaIdLokacija)
                    .HasColumnName("lokacija_id_lokacija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DogadajIdDogadaj)
                    .HasColumnName("dogadaj_id_dogadaj")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DatumDogadajaDo).HasColumnName("datum_dogadaja_do");

                entity.Property(e => e.DatumDogadajaOd).HasColumnName("datum_dogadaja_od");

                entity.HasOne(d => d.DogadajIdDogadajNavigation)
                    .WithMany(p => p.DogadajNaLokaciji)
                    .HasForeignKey(d => d.DogadajIdDogadaj)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_dogadaj_na_lokaciji_dogadaj1");

                entity.HasOne(d => d.LokacijaIdLokacijaNavigation)
                    .WithMany(p => p.DogadajNaLokaciji)
                    .HasForeignKey(d => d.LokacijaIdLokacija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_dogadaj_na_lokaciji_lokacija1");
            });

            modelBuilder.Entity<Kategorija>(entity =>
            {
                entity.HasKey(e => e.IdKategorija)
                    .HasName("PRIMARY");

                entity.ToTable("kategorija");

                entity.Property(e => e.IdKategorija)
                    .HasColumnName("id_kategorija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NazivKategorije)
                    .IsRequired()
                    .HasColumnName("naziv_kategorije")
                    .HasMaxLength(45)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<Korisnik>(entity =>
            {
                entity.HasKey(e => e.IdKorisnik)
                    .HasName("PRIMARY");

                entity.ToTable("korisnik");

                entity.HasIndex(e => e.IdClanstvo)
                    .HasName("fk_korisnik_clanstvo1_idx");

                entity.HasIndex(e => e.IdUloga)
                    .HasName("fk_korisnik_uloga1_idx");

                entity.Property(e => e.IdKorisnik)
                    .HasColumnName("id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.AdresaKorisnika)
                    .HasColumnName("adresa_korisnika")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.DatumRegistracije).HasColumnName("datum_registracije");

                entity.Property(e => e.DatumRođenja)
                    .HasColumnName("datum_rođenja")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.EmailKorisnika)
                    .IsRequired()
                    .HasColumnName("email_korisnika")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.IdClanstvo)
                    .HasColumnName("id_clanstvo")
                    .HasColumnType("int(11)");

                entity.Property(e => e.IdUloga)
                    .HasColumnName("id_uloga")
                    .HasColumnType("int(11)");

                entity.Property(e => e.ImeKorisnika)
                    .IsRequired()
                    .HasColumnName("ime_korisnika")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.KorsnickoIme)
                    .IsRequired()
                    .HasColumnName("korsnicko_ime")
                    .HasMaxLength(25)
                    .IsUnicode(false);

                entity.Property(e => e.Lozinka)
                    .IsRequired()
                    .HasColumnName("lozinka")
                    .HasMaxLength(20)
                    .IsUnicode(false);

                entity.Property(e => e.PrezimeKorisnika)
                    .IsRequired()
                    .HasColumnName("prezime_korisnika")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.SlikaKorisnika)
                    .HasColumnName("slika_korisnika")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Status)
                    .HasColumnName("status")
                    .HasMaxLength(25)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.TelefonKorisnika)
                    .HasColumnName("telefon_korisnika")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.HasOne(d => d.IdClanstvoNavigation)
                    .WithMany(p => p.Korisnik)
                    .HasForeignKey(d => d.IdClanstvo)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_korisnik_clanstvo1");

                entity.HasOne(d => d.IdUlogaNavigation)
                    .WithMany(p => p.Korisnik)
                    .HasForeignKey(d => d.IdUloga)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_korisnik_uloga1");
            });

            modelBuilder.Entity<Lokacija>(entity =>
            {
                entity.HasKey(e => e.IdLokacija)
                    .HasName("PRIMARY");

                entity.ToTable("lokacija");

                entity.HasIndex(e => e.IdKorisnik)
                    .HasName("fk_lokacija_korisnik1_idx");

                entity.Property(e => e.IdLokacija)
                    .HasColumnName("id_lokacija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.AdresaLokacije)
                    .IsRequired()
                    .HasColumnName("adresa_lokacije")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.BrojTelefona)
                    .HasColumnName("broj_telefona")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.CjenovniRazred)
                    .HasColumnName("cjenovni_razred")
                    .HasMaxLength(4)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.DatumKreiranja)
                    .IsRequired()
                    .HasColumnName("datum_kreiranja")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.Email)
                    .HasColumnName("email")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.IdKorisnik)
                    .HasColumnName("id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NazivLokacije)
                    .IsRequired()
                    .HasColumnName("naziv_lokacije")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.OibLokacije)
                    .IsRequired()
                    .HasColumnName("OIB_lokacije")
                    .HasMaxLength(11)
                    .IsUnicode(false);

                entity.Property(e => e.PristupacnostOsobeSInvaliditetom)
                    .HasColumnName("pristupacnost_osobe_s_invaliditetom")
                    .HasColumnType("tinyint(4)")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Pusenje)
                    .HasColumnName("pusenje")
                    .HasColumnType("tinyint(4)")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.RadnoVrijeme)
                    .HasColumnName("radno_vrijeme")
                    .HasMaxLength(100)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.RadnoVrijemeVikend)
                    .HasColumnName("radno_vrijeme_vikend")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.HasOne(d => d.IdKorisnikNavigation)
                    .WithMany(p => p.Lokacija)
                    .HasForeignKey(d => d.IdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_lokacija_korisnik1");
            });

            modelBuilder.Entity<Obilazak>(entity =>
            {
                entity.HasKey(e => e.IdObilazak)
                    .HasName("PRIMARY");

                entity.ToTable("obilazak");

                entity.HasIndex(e => e.IdKorisnik)
                    .HasName("fk_obilazak_korisnik1_idx");

                entity.HasIndex(e => e.IdLokacija)
                    .HasName("fk_obilazak_lokacija1_idx");

                entity.Property(e => e.IdObilazak)
                    .HasColumnName("id_obilazak")
                    .HasColumnType("int(11)");

                entity.Property(e => e.BrojLjudi)
                    .HasColumnName("broj_ljudi")
                    .HasColumnType("int(11)")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.DatumIVrijemeObilaska).HasColumnName("datum_i_vrijeme_obilaska");

                entity.Property(e => e.DatumKreiranja).HasColumnName("datum_kreiranja");

                entity.Property(e => e.IdKorisnik)
                    .HasColumnName("id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.IdLokacija)
                    .HasColumnName("id_lokacija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NazivObilaska)
                    .IsRequired()
                    .HasColumnName("naziv_obilaska")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.OpisRuteObilaska)
                    .IsRequired()
                    .HasColumnName("opis_rute_obilaska")
                    .HasColumnType("longtext");

                entity.Property(e => e.TrajanjeObilaska)
                    .HasColumnName("trajanje_obilaska")
                    .HasDefaultValueSql("'NULL'");

                entity.HasOne(d => d.IdKorisnikNavigation)
                    .WithMany(p => p.Obilazak)
                    .HasForeignKey(d => d.IdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_obilazak_korisnik1");

                entity.HasOne(d => d.IdLokacijaNavigation)
                    .WithMany(p => p.Obilazak)
                    .HasForeignKey(d => d.IdLokacija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_obilazak_lokacija1");
            });

            modelBuilder.Entity<OmiljenaLokacija>(entity =>
            {
                entity.HasKey(e => new { e.LokacijaIdLokacija, e.KorisnikIdKorisnik })
                    .HasName("PRIMARY");

                entity.ToTable("omiljena_lokacija");

                entity.HasIndex(e => e.KorisnikIdKorisnik)
                    .HasName("fk_omiljena_lokacija_korisnik1_idx");

                entity.Property(e => e.LokacijaIdLokacija)
                    .HasColumnName("lokacija_id_lokacija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.KorisnikIdKorisnik)
                    .HasColumnName("korisnik_id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DatumDodavanja).HasColumnName("datum_dodavanja");

                entity.HasOne(d => d.KorisnikIdKorisnikNavigation)
                    .WithMany(p => p.OmiljenaLokacija)
                    .HasForeignKey(d => d.KorisnikIdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_omiljena_lokacija_korisnik1");

                entity.HasOne(d => d.LokacijaIdLokacijaNavigation)
                    .WithMany(p => p.OmiljenaLokacija)
                    .HasForeignKey(d => d.LokacijaIdLokacija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_omiljena_lokacija_lokacija1");
            });

            modelBuilder.Entity<OmiljenoPivo>(entity =>
            {
                entity.HasKey(e => new { e.ProizvodIdProizvod, e.KorisnikIdKorisnik })
                    .HasName("PRIMARY");

                entity.ToTable("omiljeno_pivo");

                entity.HasIndex(e => e.KorisnikIdKorisnik)
                    .HasName("fk_omiljeno_pivo_korisnik1_idx");

                entity.Property(e => e.ProizvodIdProizvod)
                    .HasColumnName("proizvod_id_proizvod")
                    .HasColumnType("int(11)");

                entity.Property(e => e.KorisnikIdKorisnik)
                    .HasColumnName("korisnik_id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DatumDodavanja)
                    .HasColumnName("datum_dodavanja")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.HasOne(d => d.KorisnikIdKorisnikNavigation)
                    .WithMany(p => p.OmiljenoPivo)
                    .HasForeignKey(d => d.KorisnikIdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_omiljeno_pivo_korisnik1");

                entity.HasOne(d => d.ProizvodIdProizvodNavigation)
                    .WithMany(p => p.OmiljenoPivo)
                    .HasForeignKey(d => d.ProizvodIdProizvod)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_omiljeno_pivo_proizvod1");
            });

            modelBuilder.Entity<Proizvod>(entity =>
            {
                entity.HasKey(e => e.IdProizvod)
                    .HasName("PRIMARY");

                entity.ToTable("proizvod");

                entity.HasIndex(e => e.IdKategorija)
                    .HasName("fk_proizvod_kategorija_idx");

                entity.Property(e => e.IdProizvod)
                    .HasColumnName("id_proizvod")
                    .HasColumnType("int(11)");

                entity.Property(e => e.CijenaProizvoda).HasColumnName("cijena_proizvoda");

                entity.Property(e => e.DatumKreiranja)
                    .IsRequired()
                    .HasColumnName("datum_kreiranja")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.IdKategorija)
                    .HasColumnName("id_kategorija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.Litara)
                    .HasColumnName("litara")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.NazivProizvoda)
                    .IsRequired()
                    .HasColumnName("naziv_proizvoda")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.Okus)
                    .HasColumnName("okus")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.PostotakAlkohola)
                    .HasColumnName("postotak_alkohola")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Proizvodac)
                    .HasColumnName("proizvodac")
                    .HasMaxLength(45)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.ZemljaPorjekla)
                    .HasColumnName("zemlja_porjekla")
                    .HasMaxLength(3)
                    .IsUnicode(false)
                    .HasDefaultValueSql("'NULL'");

                entity.HasOne(d => d.IdKategorijaNavigation)
                    .WithMany(p => p.Proizvod)
                    .HasForeignKey(d => d.IdKategorija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_proizvod_kategorija");
            });

            modelBuilder.Entity<ProizvodNaPromociji>(entity =>
            {
                entity.HasKey(e => new { e.PromocijaIdPromocija, e.ProizvodIdProizvod })
                    .HasName("PRIMARY");

                entity.ToTable("proizvod_na_promociji");

                entity.HasIndex(e => e.ProizvodIdProizvod)
                    .HasName("fk_proizvod_na_promociji_proizvod1_idx");

                entity.Property(e => e.PromocijaIdPromocija)
                    .HasColumnName("promocija_id_promocija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.ProizvodIdProizvod)
                    .HasColumnName("proizvod_id_proizvod")
                    .HasColumnType("int(11)");

                entity.HasOne(d => d.ProizvodIdProizvodNavigation)
                    .WithMany(p => p.ProizvodNaPromociji)
                    .HasForeignKey(d => d.ProizvodIdProizvod)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_proizvod_na_promociji_proizvod1");

                entity.HasOne(d => d.PromocijaIdPromocijaNavigation)
                    .WithMany(p => p.ProizvodNaPromociji)
                    .HasForeignKey(d => d.PromocijaIdPromocija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_proizvod_na_promociji_promocija1");
            });

            modelBuilder.Entity<Promocija>(entity =>
            {
                entity.HasKey(e => e.IdPromocija)
                    .HasName("PRIMARY");

                entity.ToTable("promocija");

                entity.HasIndex(e => e.IdKorisnik)
                    .HasName("fk_promocija_korisnik1_idx");

                entity.Property(e => e.IdPromocija)
                    .HasColumnName("id_promocija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DatumDo).HasColumnName("datum_do");

                entity.Property(e => e.DatumKreiranjaPromocije)
                    .HasColumnName("datum_kreiranja_promocije")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.DatumOd).HasColumnName("datum_od");

                entity.Property(e => e.IdKorisnik)
                    .HasColumnName("id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NazivPromocije)
                    .IsRequired()
                    .HasColumnName("naziv_promocije")
                    .HasMaxLength(45)
                    .IsUnicode(false);

                entity.Property(e => e.OpisPromocije)
                    .IsRequired()
                    .HasColumnName("opis_promocije")
                    .HasColumnType("longtext");

                entity.Property(e => e.PrimjenjiviPopust)
                    .HasColumnName("primjenjivi_popust")
                    .HasColumnType("int(11)");

                entity.HasOne(d => d.IdKorisnikNavigation)
                    .WithMany(p => p.Promocija)
                    .HasForeignKey(d => d.IdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_promocija_korisnik1");
            });

            modelBuilder.Entity<PromocijaNaLokaciji>(entity =>
            {
                entity.HasKey(e => new { e.LokacijaIdLokacija, e.PromocijaIdPromocija })
                    .HasName("PRIMARY");

                entity.ToTable("promocija_na_lokaciji");

                entity.HasIndex(e => e.PromocijaIdPromocija)
                    .HasName("fk_promocija_na_lokaciji_promocija1_idx");

                entity.Property(e => e.LokacijaIdLokacija)
                    .HasColumnName("lokacija_id_lokacija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.PromocijaIdPromocija)
                    .HasColumnName("promocija_id_promocija")
                    .HasColumnType("int(11)");

                entity.HasOne(d => d.LokacijaIdLokacijaNavigation)
                    .WithMany(p => p.PromocijaNaLokaciji)
                    .HasForeignKey(d => d.LokacijaIdLokacija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_promocija_na_lokaciji_lokacija1");

                entity.HasOne(d => d.PromocijaIdPromocijaNavigation)
                    .WithMany(p => p.PromocijaNaLokaciji)
                    .HasForeignKey(d => d.PromocijaIdPromocija)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_promocija_na_lokaciji_promocija1");
            });

            modelBuilder.Entity<Recenzija>(entity =>
            {
                entity.HasKey(e => e.IdRecenzija)
                    .HasName("PRIMARY");

                entity.ToTable("recenzija");

                entity.HasIndex(e => e.IdKorisnik)
                    .HasName("fk_recenzija_korisnik1_idx");

                entity.HasIndex(e => e.IdLokacija)
                    .HasName("fk_recenzija_lokacija1_idx");

                entity.HasIndex(e => e.IdProizvod)
                    .HasName("fk_recenzija_proizvod1_idx");

                entity.Property(e => e.IdRecenzija)
                    .HasColumnName("id_recenzija")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DatumIVrijemeRecenzije).HasColumnName("datum_i_vrijeme_recenzije");

                entity.Property(e => e.IdKorisnik)
                    .HasColumnName("id_korisnik")
                    .HasColumnType("int(11)");

                entity.Property(e => e.IdLokacija)
                    .HasColumnName("id_lokacija")
                    .HasColumnType("int(11)")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.IdProizvod)
                    .HasColumnName("id_proizvod")
                    .HasColumnType("int(11)")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Komentar)
                    .HasColumnName("komentar")
                    .HasColumnType("mediumtext")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Ocjena)
                    .HasColumnName("ocjena")
                    .HasColumnType("int(11)");

                entity.HasOne(d => d.IdKorisnikNavigation)
                    .WithMany(p => p.Recenzija)
                    .HasForeignKey(d => d.IdKorisnik)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_recenzija_korisnik1");

                entity.HasOne(d => d.IdLokacijaNavigation)
                    .WithMany(p => p.Recenzija)
                    .HasForeignKey(d => d.IdLokacija)
                    .HasConstraintName("fk_recenzija_lokacija1");

                entity.HasOne(d => d.IdProizvodNavigation)
                    .WithMany(p => p.Recenzija)
                    .HasForeignKey(d => d.IdProizvod)
                    .HasConstraintName("fk_recenzija_proizvod1");
            });

            modelBuilder.Entity<StavkaDegustacijskogMenija>(entity =>
            {
                entity.HasKey(e => new { e.ProizvodIdProizvod, e.DegustacijskiMeniIdDegustacijskiMeni })
                    .HasName("PRIMARY");

                entity.ToTable("stavka_degustacijskog_menija");

                entity.HasIndex(e => e.DegustacijskiMeniIdDegustacijskiMeni)
                    .HasName("fk_stavka_degustacijskog_menija_degustacijski_meni1_idx");

                entity.HasIndex(e => e.ProizvodIdProizvod)
                    .HasName("fk_stavka_degustacijskog_menija_proizvod1_idx");

                entity.Property(e => e.ProizvodIdProizvod)
                    .HasColumnName("proizvod_id_proizvod")
                    .HasColumnType("int(11)");

                entity.Property(e => e.DegustacijskiMeniIdDegustacijskiMeni)
                    .HasColumnName("degustacijski_meni_id_degustacijski_meni")
                    .HasColumnType("int(11)");

                entity.Property(e => e.Kolicina)
                    .HasColumnName("kolicina")
                    .HasColumnType("int(11)");

                entity.HasOne(d => d.DegustacijskiMeniIdDegustacijskiMeniNavigation)
                    .WithMany(p => p.StavkaDegustacijskogMenija)
                    .HasForeignKey(d => d.DegustacijskiMeniIdDegustacijskiMeni)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_stavka_degustacijskog_menija_degustacijski_meni1");

                entity.HasOne(d => d.ProizvodIdProizvodNavigation)
                    .WithMany(p => p.StavkaDegustacijskogMenija)
                    .HasForeignKey(d => d.ProizvodIdProizvod)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_stavka_degustacijskog_menija_proizvod1");
            });

            modelBuilder.Entity<Uloga>(entity =>
            {
                entity.HasKey(e => e.IdUloga)
                    .HasName("PRIMARY");

                entity.ToTable("uloga");

                entity.Property(e => e.IdUloga)
                    .HasColumnName("id_uloga")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NazivUloge)
                    .IsRequired()
                    .HasColumnName("naziv_uloge")
                    .HasMaxLength(25)
                    .IsUnicode(false);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
